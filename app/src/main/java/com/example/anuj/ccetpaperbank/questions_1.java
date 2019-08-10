package com.example.anuj.ccetpaperbank;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


public class questions_1 extends Fragment {

    private Button download;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_qpaper1, container, false);

        download = view.findViewById(R.id.button);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri path = Uri.parse("https://firebasestorage.googleapis.com/v0/b/ccet-paper-bank-b9f8f.appspot.com/o/00896533.pdf?alt=media&token=07c6763c-d368-4be1-9efa-f17e4077c53e");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(path, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);        }
        });

        return view;

    }







}

