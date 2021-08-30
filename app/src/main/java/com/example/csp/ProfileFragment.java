package com.example.csp;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import kotlin.Unit;

public class ProfileFragment extends Fragment{

    TextView profemail, profphone, profname;
    ImageView imageView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

//        profemail = getSupportFragmentManager().findFragmentById(R.id.emailProfile);
//        profphone = findViewById(R.id.phoneProfile);
//        profname = findViewById(R.id.nameProfile);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);




        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentid = null;
        if (user != null) {
            currentid = user.getUid();
        }
        DocumentReference reference;
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        reference = firestore.collection("customers").document(Objects.requireNonNull(currentid));

        reference.get()
                .addOnCompleteListener(task -> {

                    if (Objects.requireNonNull(task.getResult()).exists()){
                        //fname, email, phone

                        String nameResult = task.getResult().getString("fname");
                        String phoneResult = task.getResult().getString("phone");
                        String emailResult = task.getResult().getString("email");

                        //to be changed
                        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);
                        profemail.setText(emailResult);
                        profname.setText(phoneResult);
                        profname.setText(nameResult);

                    }else{
                        //if user doesn't exist they are routed to create a profile
                        Intent intent = new Intent(getActivity(), RegisterUser.class);
                        startActivity(intent);
                    }

                });




    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getActivity().findViewById(R.id.imageProfile);
        profemail = getActivity().findViewById(R.id.emailProfile);
        profname = getActivity().findViewById(R.id.nameProfile);
        profphone = getActivity().findViewById(R.id.phoneProfile);
    }





}
