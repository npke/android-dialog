package happycoding.kenp.dialogexample;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FragmentDialog.FragmentDialogListener {

    Button btnStandardDialog, btnCustomDialog, btnFragmentDialog;
    Button btnSpinnerProgress, btnHorizontalProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStandardDialog = (Button) findViewById(R.id.standard_dialog);
        btnCustomDialog = (Button) findViewById(R.id.custom_dialog);
        btnFragmentDialog = (Button) findViewById(R.id.fragment_dialog);

        btnSpinnerProgress = (Button) findViewById(R.id.spinner_progress);
        btnHorizontalProgress = (Button) findViewById(R.id.horizontal_progress);

        btnHorizontalProgress.setOnClickListener(this);
        btnSpinnerProgress.setOnClickListener(this);
        btnStandardDialog.setOnClickListener(this);
        btnCustomDialog.setOnClickListener(this);
        btnFragmentDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.standard_dialog)
            showStandardDialog();
        else if (v.getId() == R.id.custom_dialog)
            showCustomDialog();
        else if (v.getId() == R.id.fragment_dialog)
            showFragmentDialog();
        else if (v.getId() == R.id.spinner_progress)
            showSpinnerProgress();
        else if (v.getId() == R.id.horizontal_progress)
            showHorizontalProgress();
    }

    private void showHorizontalProgress() {
        final ProgressDialog process = new ProgressDialog(this);

        process.setMessage("Download is in process...");
        process.setTitle("Download Resources");
        process.setMax(100);
        process.setIndeterminate(false);
        process.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        process.setCancelable(false);
        process.setCanceledOnTouchOutside(false);
        process.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int processValue = 0;
                    while (processValue < 100)
                    {
                        Thread.sleep(100);
                        processValue += 5;
                        process.setProgress(processValue);
                    }

                    process.dismiss();
                } catch (Throwable t)
                {

                }

            }
        }).start();
    }

    private void showSpinnerProgress() {
        ProgressDialog process = new ProgressDialog(this);

        process.setMessage("Download is in process...");
        process.setTitle("Download Resources");
        process.setMax(200);
        process.setIndeterminate(false);
        process.setProgress(50);
        process.show();
    }

    private void showFragmentDialog() {
        FragmentDialog fragmentDialog = new FragmentDialog();
        fragmentDialog.show(getFragmentManager(), "STRING_TAG");
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Custom Alert Dialog")
                .setMessage("Are you sure want to close this?\n")
                .setView(R.layout.custom_dialog)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "USER SAYS YES", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "NOOOoo...", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Remind later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Not now baby", Toast.LENGTH_SHORT).show();
                    }
                });

        builder.create().show();
    }

    private void showStandardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Standard Alert Dialog")
        .setMessage("Are you sure want to close this?\n")
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "USER SAYS YES", Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "NOOOoo...", Toast.LENGTH_SHORT).show();
            }
        })
        .setNeutralButton("Remind later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Not now baby", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    @Override
    public void onDialogPositive(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "ACTIVITY: USER SAYS YES", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegative(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "ACTIVITY: USER SAYS NO", Toast.LENGTH_SHORT).show();
    }
}
