package com.interview.client_rs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class TableFragment  extends Fragment {
    Button selectAll;
    Button deleteAll;
    TableLayout tableLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table_fragment, container, false);
        selectAll = (Button)view.findViewById(R.id.select_all);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAll(view);
            }
        });
        deleteAll = (Button)view.findViewById(R.id.delete_all);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll(view);
            }
        });
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        return view;
    }

    public void selectAll(View v) {
        try {
            tableLayout.removeAllViews();
            TableRow header = new TableRow(getContext());
            header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            for (int i=0; i <= Value.maxIndex; i++) {
                TextView headerTextView = new TextView(getContext());
                headerTextView.setText("  "+Value.getFieldName(i)+"  ");
                headerTextView.setTextSize(Requester.textSize);
                header.addView(headerTextView, i);
            }
            tableLayout.addView(header, 0);
            Requester.getApi().selectall().enqueue(new Callback<List<Value>>() {
                @Override
                public void onResponse(Call<List<Value>> call, Response<List<Value>> response) {
                    try {
                        for (Value resp:response.body()){
                            TableRow tableRow = new TableRow(getContext());
                            tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                                    LayoutParams.WRAP_CONTENT));
                            for (int i=0; i <= Value.maxIndex; i++) {
                                TextView rowTextView = new TextView(getContext());
                                rowTextView.setText("  "+resp.getFieldValue(i)+"  ");
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
        } catch (Exception e) {
            Log.e("Log",
                    "select all - error: " + e.getMessage());
        }
    }

    public void deleteAll(View v) {
        try {
            Requester.getApi().deleteall().enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                }
                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {
                    Log.e("Log",
                            "call - error: " + t.getMessage());
                }
            });
            selectAll(getView());
            Toast.makeText(getContext(), "All records were deleted", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Log",
                    "select all - error: " + e.getMessage());
        }
    }
}