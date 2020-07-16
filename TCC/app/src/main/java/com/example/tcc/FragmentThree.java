package com.example.tcc;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

import javax.annotation.Nullable;

public class FragmentThree extends Fragment {

    TextView a,b,c,d,m,f;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_three);
        a=(TextView) a.findViewById(R.id.txtnome);
        b=(TextView) b.findViewById(R.id.txtnascimento);
        c=(TextView) c.findViewById(R.id.txtcpf);
        d=(TextView) d.findViewById(R.id.txttel);
        m=(TextView) m.findViewById(R.id.txtcep);
        f=(TextView) f.findViewById(R.id.txtnum);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("Aluno").document(userId);
        documentReference.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                a.setText(documentSnapshot.getString("nome"));
                b.setText(documentSnapshot.getString("nasc"));
                c.setText(documentSnapshot.getString("cpf"));
                d.setText(documentSnapshot.getString("tel"));
                f.setText(documentSnapshot.getString("num"));
                m.setText(documentSnapshot.getString("cep"));

            }
        });


    }

    private void setContentView(int fragment_three) {
    }
    /*
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_three, container, false);
        return view;
    }

     */
}
