package happycoding.kenp.dialogexample;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by kenp on 14/08/2016.
 */
public class FragmentDialog extends DialogFragment{

    public interface FragmentDialogListener {
        void onDialogPositive(DialogFragment dialog);
        void onDialogNegative(DialogFragment dialog);
    }

    FragmentDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Fragment Alert Dialog")
                .setMessage("Are you sure want to close this?\n")
                .setView(R.layout.custom_dialog)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogNegative(FragmentDialog.this);
                        //Toast.makeText(getActivity(), "USER SAYS YES", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getActivity(), "NOOOoo...", Toast.LENGTH_SHORT).show();
                        mListener.onDialogNegative(FragmentDialog.this);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            mListener = (FragmentDialogListener) activity;
        } catch (Exception e)
        {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
