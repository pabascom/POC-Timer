package phil.homework.week1day4take2timer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.Toast

class CameraAlertDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return AlertDialog.Builder(activity).apply{
            setPositiveButton("+", DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->  
                Toast.makeText(this.context, "You clicked a button!", Toast.LENGTH_SHORT).show()
            })
        }.create()
    }
}