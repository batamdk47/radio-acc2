package radio.elenahudson.uk.ulti.network;

import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import radio.elenahudson.uk.HomeActivity;
import radio.elenahudson.uk.R;
import radio.elenahudson.uk.RadioObject;
import radio.elenahudson.uk.components.ListviewAdapter;

import static radio.elenahudson.uk.R.id.listViewRadio;


public class As extends AsyncTask<String, Integer, ArrayList<RadioObject>> {
    HomeActivity context;
    ListView listView, listViewCategory;
    ArrayList<RadioObject> radioObjectsCountry = new ArrayList<>();
    ArrayList<RadioObject> radioObjectsCategory = new ArrayList<>();
    ArrayList<String> arrayListCategory = new ArrayList<>();
    ListviewAdapter adapterCountry = null;
    EditText editText_search;
    LinearLayout linearLayoutCategory, linearLayoutMenu;
    View viewCover,viewCountry;
    ProgressBar progressBar;

    //constructor này được truyền vào là MainActivity
    public As(HomeActivity ctx) {
        context = ctx;
        listView = (ListView) context.findViewById(listViewRadio);
        listViewCategory =(ListView)context.findViewById(R.id.listView_Category);
        linearLayoutMenu =(LinearLayout)context.findViewById(R.id.linearlayout_menu);
        linearLayoutCategory =(LinearLayout)context.findViewById(R.id.linearlayout_Listview_category);
        viewCover = (View)context.findViewById(R.id.coverView);
        viewCountry = (View)context.findViewById(R.id.viewCountry);
        progressBar =(ProgressBar)context.findViewById(R.id.progressBarBegin);

        EdittextSearch();
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                radioObjectsCategory.clear();
                linearLayoutMenu.setVisibility(View.GONE);
                linearLayoutCategory.setVisibility(View.GONE);
                viewCover.setVisibility(View.GONE);
                viewCountry.setVisibility(View.GONE);
                String str = arrayListCategory.get(position);
//                linearlayout_Listview_category.setVisibility(View.GONE);
//                viewCountry.setVisibility(View.GONE);
//                linearLayout_menu.setVisibility(View.GONE);
//                coverView.setVisibility(View.GONE);
                for (int i = 0; i < radioObjectsCountry.size(); i++) {
                    if (str.equals("Other")) {
                        if (radioObjectsCountry.get(i).category.equals("All")) {
                            radioObjectsCategory.add(radioObjectsCountry.get(i));
                        }
                    } else {
                        if (radioObjectsCountry.get(i).category.equals(str)) {
                            radioObjectsCategory.add(radioObjectsCountry.get(i));
                        }
                    }
                }
                adapterCountry = new ListviewAdapter(context, radioObjectsCategory);
                listView.setAdapter(adapterCountry);
            }
        });

    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList doInBackground(String... params) {

        GetDataCountry.GetData(radioObjectsCountry,arrayListCategory, params[0]);
        return radioObjectsCountry;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {


    }

    @Override
    protected void onPostExecute(ArrayList<RadioObject> s) {
        progressBar.setVisibility(View.GONE);
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.item_category,arrayListCategory);
        listViewCategory.setAdapter(arrayAdapter);
        adapterCountry = new ListviewAdapter(context, radioObjectsCountry);
        listView.setAdapter(adapterCountry);
    }
    public ArrayList<String> GetArraylistCategory(ArrayList<String> arrayList){
        arrayList.addAll(arrayListCategory);
        return arrayList;
    }
    public void EdittextSearch() {
        editText_search = (EditText)context.findViewById(R.id.edt_search);
        editText_search.requestFocus();
        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (radioObjectsCountry.size() > 0 && adapterCountry != null) {
                    String textSearch = editText_search.getText().toString().toLowerCase().trim();
                    adapterCountry.resetdata(textSearch);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }
}


