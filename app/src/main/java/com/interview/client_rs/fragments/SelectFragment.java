package com.interview.client_rs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.interview.client_rs.R;
import com.interview.client_rs.Requester;
import com.interview.client_rs.ws.PostResponse;
import com.interview.client_rs.ws.Value;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectFragment extends Fragment {
    EditText etId;
    EditText etName;
    EditText etCategory;
    EditText etDescription;
    Button selectByOne;
    Button selectByFew;
    TableLayout tableLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_fragment, container, false);
        etId = (EditText)view.findViewById(R.id.et_id);
        etName = (EditText)view.findViewById(R.id.et_name);
        etCategory = (EditText)view.findViewById(R.id.et_category);
        etDescription = (EditText)view.findViewById(R.id.et_description);
        selectByOne = (Button)view.findViewById(R.id.select_by_one);
        selectByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectByOne(view);
            }
        });
        selectByFew = (Button)view.findViewById(R.id.select_by_few);
        selectByFew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectByFew(view);
            }
        });
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        return view;
    }

    public void selectByOne(View v) {
        try {
            if (!etId.getText().toString().isEmpty()) {
                headerPrint(v);
                Requester.getApi().selectbyid(Long.parseLong(etId.getText().toString())).enqueue(new Callback<PostResponse>() {
                    @Override
                    public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                        try {
                            TableRow tableRow = new TableRow(getContext());
                            tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                                    LayoutParams.WRAP_CONTENT));
                            for (int i=0; i <= Value.maxIndex; i++) {
                                TextView rowTextView = new TextView(getContext());
                                rowTextView.setText("  " + response.body().getValue().getFieldValue(i) + "  ");
                                rowTextView.setTextSize(Requester.textSize);
                                tableRow.addView(rowTextView, i);
                            }
                            tableLayout.addView(tableRow, 1);
                        } catch (Exception e) {
                            Log.e("Log",
                                    "result - error: " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResponse> call, Throwable t) {
                        Log.e("Log",
                                "call - error: " + t.getMessage());
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please input ID", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Log",
                    "insert - error: " + e.getMessage());
        }
    }

    public void selectByFew(View v) {
        try {
            if (!etName.getText().toString().isEmpty() &&
                    !etCategory.getText().toString().isEmpty() &&
                    !etDescription.getText().toString().isEmpty()) {
                headerPrint(v);
                Requester.getApi().selectFew(etName.getText().toString(),
                        Integer.parseInt(etCategory.getText().toString()),
                        etDescription.getText().toString()).enqueue(new Callback<List<Value>>() {
                    @Override
                    public void onResponse(Call<List<Value>> call, Response<List<Value>> response) {
                        try {
                            for (Value resp:response.body()){
                                TableRow tableRow = new TableRow(getContext());
                                tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                                        LayoutParams.WRAP_CONTENT));
                                for (int i=0; i <= Value.maxIndex; i++) {
                                    TextView rowTextView = new TextView(getContext());
                                    rowTextView.setText("  " + resp.getFieldValue(i) + "  ");
                                    rowTextView.setTextSize(Requester.textSize);
                                    tableRow.addView(rowTextView, i);
                                }
                                tableLayout.addView(tableRow, response.body().indexOf(resp)+1);
                            }
                        } catch (Exception e) {
                            Log.e("Log",
                                    "result - error: " + e.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Value>> call, Throwable t) {
                        Log.e("Log",
                                "call - error: " + t.getMessage());
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please input Name, Category and Description", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Log",
                    "insert - error: " + e.getMessage());
        }
    }

    private void headerPrint(View v){
        tableLayout.removeAllViews();
        TableRow header = new TableRow(getContext());
        header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        for (int i = 0; i <= Value.maxIndex; i++) {
            TextView headerTextView = new TextView(getContext());
            headerTextView.setText("  " + Value.getFieldName(i)+"  ");
            headerTextView.setTextSize(Requester.textSize);
            headerTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            header.addView(headerTextView, i);
        }
        tableLayout.addView(header, 0);
    }
}
