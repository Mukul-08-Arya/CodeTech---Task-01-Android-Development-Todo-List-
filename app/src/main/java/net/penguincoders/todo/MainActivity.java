package net.penguincoders.todo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button add;
    AlertDialog dialog;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.add);
        layout=findViewById(R.id.container);

        buildDialog();
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void buildDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder( this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name= view.findViewById(R.id.nameEdit);

        builder.setView(view);
        builder.setTitle("Enter Your Task")
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addCard(name.getText().toString());
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog = builder.create();
    }
    private void addCard(String name){
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);
        nameView.setText(name);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }
}