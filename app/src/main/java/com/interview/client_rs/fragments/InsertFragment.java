package com.interview.client_rs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.interview.client_rs.R;
import com.interview.client_rs.Requester;
import com.interview.client_rs.ws.PostResponse;
import java.net.URLEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFragment extends Fragment {
    EditText etName;
    EditText etJndiName;
    EditText etDescription;
    EditText etCategory;
    EditText etUrl;
    EditText etNickname;
    EditText etPassword;
    EditText etTimeout;
    EditText etMaxConnects;
    EditText etMinConnects;
    Button insert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment, container, false);
        etName = (EditText)view.findViewById(R.id.et_name);
        etJndiName = (EditText)view.findViewById(R.id.et_jndi_name);
        etDescription = (EditText)view.findViewById(R.id.et_description);
        etCategory = (EditText)view.findViewById(R.id.et_category);
        etUrl = (EditText)view.findViewById(R.id.et_url);
        etNickname = (EditText)view.findViewById(R.id.et_nickname);
        etPassword = (EditText)view.findViewById(R.id.et_password);
        etTimeout = (EditText)view.findViewById(R.id.et_timeout);
        etMaxConnects = (EditText)view.findViewById(R.id.et_max_connects);
        etMinConnects = (EditText)view.findViewById(R.id.et_min_connects);
        insert = (Button)view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert(view);
            }
        });
        return view;
    }

    public void insert(View v) {
        try {
            if (!etName.getText().toString().isEmpty() && !etJndiName.getText().toString().isEmpty() && !etDescription.getText().toString().isEmpty()  &&
                    !etCategory.getText().toString().isEmpty() && !etUrl.getText().toString().isEmpty() && !etNickname.getText().toString().isEmpty() &&
                    !etPassword.getText().toString().isEmpty() && !etTimeout.getText().toString().isEmpty() && !etMaxConnects.getText().toString().isEmpty() &&
                    !etMinConnects.getText().toString().isEmpty()) {
                String name = URLEncoder.encode(etName.getText().toString(), "UTF-8");
                String jndi_name = URLEncoder.encode(etJndiName.getText().toString(), "UTF-8");
                String description = URLEncoder.encode(etDescription.getText().toString(), "UTF-8");
                int category = Integer.parseInt(etCategory.getText().toString());
                String url = URLEncoder.encode(etUrl.getText().toString(), "UTF-8");
                String nickname = URLEncoder.encode(etNickname.getText().toString(), "UTF-8");
                String password = URLEncoder.encode(etPassword.getText().toString(), "UTF-8");
                float timeout = Float.parseFloat(etTimeout.getText().toString());
                int max_connects = Integer.parseInt(etMaxConnects.getText().toString());
                int mix_connects = Integer.parseInt(etMinConnects.getText().toString());
                Requester.getApi().insert(name, jndi_name, description, category, url, nickname, password, timeout, max_connects, mix_connects).enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    }
                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.e("Log",
                                "call - error: " + t.getMessage());
                    }
                });
                Toast.makeText(getContext(), "New record was successfully added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please input all parameters", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Log",
                    "insert - error: " + e.getMessage());
        }
    }
}
