package radio.elenahudson.uk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.exoplayer.util.Util;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import radio.elenahudson.uk.components.CountryAdapter;
import radio.elenahudson.uk.components.ListviewAdapter;
import radio.elenahudson.uk.components.TimerAdapter;
import radio.elenahudson.uk.country.ArrayCountry;
import radio.elenahudson.uk.ulti.general.General;
import radio.elenahudson.uk.ulti.general.GetArraylist;
import radio.elenahudson.uk.ulti.general.SaveArraylist;
import radio.elenahudson.uk.ulti.network.As;
import radio.elenahudson.uk.ulti.network.CheckConectNetwork;
import radio.elenahudson.uk.ulti.network.GetData;
import radio.elenahudson.uk.ulti.service.StopNotification;
import radio.elenahudson.uk.ulti.service.startNotification;
import radio.elenahudson.uk.ulti.timer.ArrayTimer;
import radio.elenahudson.uk.ulti.timer.CountdownTimer;

import static radio.elenahudson.uk.ulti.Constants.BUFFER_SEGMENT_COUNT;
import static radio.elenahudson.uk.ulti.Constants.BUFFER_SEGMENT_SIZE;
import static radio.elenahudson.uk.ulti.timer.CountdownTimer.countDownTimer;


public class HomeActivity extends AppCompatActivity {
    Button btn_menu, btn_close_search, btn_search, btn_play_pause, btn_add_favorite, btn_close_timer, btn_stop_countdown_timer, btn_exit, btn_cancel;
    TextView textView_name_app, textViewStation, textViewCountdownTimer, textViewCountdownTimer1, textViewRateApp;
    EditText editText_search;
    LinearLayout linearLayout_menu, linearLayout_home, linearLayout_rate_app, linearLayout_privacy_policy, linearLayout_favorite, linearLayout_timer, linearLayout_menu_timer, linearLayout_share_app, linearlayout_country, linearlayout_Listview_country, linearlayout_Listview_category, linearlayout_category;
    RelativeLayout relativeLayoutExit;
    View coverView, timerView, viewCountry;
    ArrayList<RadioObject> radioObjects = new ArrayList<RadioObject>();
    ArrayList<RadioObject> favoriteObject = new ArrayList<RadioObject>();
    ArrayList<RadioObject> arrayListCategoryObject = new ArrayList<RadioObject>();
    ArrayList<String> arrayListCategory = new ArrayList<>();
    ListviewAdapter listviewAdapter;
    ListView listViewTimer;
    ListView listViewRadio;
    ListView listViewCountry;
    ListView listView_Category;
    public static ExoPlayer exoPlayer = ExoPlayer.Factory.newInstance(1);
    public static PlayerControl playercontrol;
    ProgressBar progressBar,progressBarBegin;
    RadioObject object;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    boolean phoneStateListenerFlag, favoritesFlag, exitTimerFlag = false, resetFlag = false, exitMenuFlag = false, exitListviewCoutry = false, exitListviewCategory = false, exitFlag = false;
    static boolean Flag = false, countryFlag = false;
    private AdView avBanner;
    private AdRequest adRequest;
    private InterstitialAd mInterstitialAd;
    ImageView imageFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_home);

        init();
        MODE_NOMAL();
        onclick();
        ShowAd();
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (GetArraylist.getAraylist(HomeActivity.this, "favorite") != null) {
            favoriteObject.clear();
            favoriteObject.addAll(GetArraylist.getAraylist(HomeActivity.this, "favorite"));
        }

        if (CheckConectNetwork.CheckConectNetwork(HomeActivity.this) == true) {
            if (playercontrol != null && countryFlag == true) {
                String string = General.getStringPreference(HomeActivity.this, "country").replace(" ", "").toLowerCase();
                String str = General.getStringPreference(HomeActivity.this, "country");
                String s = String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
                textView_name_app.setText(s + " Radio");
                As as = new As(HomeActivity.this);
                as.execute(string);

            } else {
                new ASData().execute();
            }


            if (playercontrol != null) {
                btn_play_pause.setBackgroundResource(R.drawable.pause);
                textViewStation.setText(General.getStringPreference(this, "Key_RadioName"));
            }

            if (countDownTimer != null) {
                CountdownTimer.CountdownTimer(HomeActivity.this, General.getLongPreference(HomeActivity.this, "millisUntilFinished") / 60 / 1000, textViewCountdownTimer, textViewCountdownTimer1);
            }


        } else {
            if (GetArraylist.getAraylist(HomeActivity.this, "radioobject") != null) {
                radioObjects.addAll(GetArraylist.getAraylist(HomeActivity.this, "radioobject"));
                listviewAdapter = new ListviewAdapter(HomeActivity.this, radioObjects);
                listViewRadio.setAdapter(listviewAdapter);
            }

            Toast("Check your network and restart application! Please");
        }
    }

    public void init() {
        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_close_search = (Button) findViewById(R.id.btn_close_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_add_favorite = (Button) findViewById(R.id.btn_add_favorite);
        textView_name_app = (TextView) findViewById(R.id.txtv_name_app);
        textViewCountdownTimer = (TextView) findViewById(R.id.textView_countdownTimer);
        textViewCountdownTimer1 = (TextView) findViewById(R.id.textView_countdownTimer1);
        editText_search = (EditText) findViewById(R.id.edt_search);
        linearLayout_menu = (LinearLayout) findViewById(R.id.linearlayout_menu);
        coverView = (View) findViewById(R.id.coverView);
        timerView = (View) findViewById(R.id.timerView);
        viewCountry = (View) findViewById(R.id.viewCountry);

        listViewRadio = (ListView) findViewById(R.id.listViewRadio);
        listViewTimer = (ListView) findViewById(R.id.listView_timer);
        listViewCountry = (ListView) findViewById(R.id.listView_Coutry);
        listView_Category = (ListView) findViewById(R.id.listView_Category);

        linearLayout_home = (LinearLayout) findViewById(R.id.linearlayout_home);
        linearlayout_country = (LinearLayout) findViewById(R.id.linearlayout_country);
        linearlayout_Listview_country = (LinearLayout) findViewById(R.id.linearlayout_Listview_country);
        linearlayout_Listview_category = (LinearLayout) findViewById(R.id.linearlayout_Listview_category);

        linearLayout_rate_app = (LinearLayout) findViewById(R.id.linearlayout_rate_app);
        linearLayout_share_app = (LinearLayout) findViewById(R.id.linearlayout_share_app);
        linearlayout_category = (LinearLayout) findViewById(R.id.linearlayout_category);
        linearLayout_privacy_policy = (LinearLayout) findViewById(R.id.linearlayout_privacy_policy);
        linearLayout_favorite = (LinearLayout) findViewById(R.id.linearlayout_favorite);
        linearLayout_timer = (LinearLayout) findViewById(R.id.linearlayout_timer);
        linearLayout_menu_timer = (LinearLayout) findViewById(R.id.linearlayout_sleeptimer);
        relativeLayoutExit = (RelativeLayout) findViewById(R.id.relative_exit);
        //volumeSeekbar = (SeekBar) findViewById(R.id.seekbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarBegin = (ProgressBar) findViewById(R.id.progressBarBegin);

        textViewStation = (TextView) findViewById(R.id.textViewStation);
        btn_play_pause = (Button) findViewById(R.id.btn_play_pause);
        btn_close_timer = (Button) findViewById(R.id.button_close_timer);
        btn_stop_countdown_timer = (Button) findViewById(R.id.button_close_countdownTimer);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        avBanner = (AdView) findViewById(R.id.av_banner);
        textViewRateApp = (TextView) findViewById(R.id.txtv_rate_app);

        imageFlag = (ImageView) findViewById(R.id.imgFlag);
    }

    public void MODE_SEARCH() {
        editText_search.setVisibility(View.VISIBLE);
        btn_search.setVisibility(View.GONE);
        btn_close_search.setVisibility(View.VISIBLE);
        textView_name_app.setVisibility(View.GONE);
    }

    public void MODE_NOMAL() {
        editText_search.setVisibility(View.GONE);
        btn_search.setVisibility(View.VISIBLE);
        btn_close_search.setVisibility(View.GONE);
        textView_name_app.setVisibility(View.VISIBLE);
    }

    public void ShowAd() {
        adRequest = new AdRequest.Builder().build();
        avBanner.loadAd(adRequest);
    }

    private void showFullAds() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.ad_fullscreen));
        AdRequest adRequestInter = new AdRequest.Builder().build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });

        mInterstitialAd.loadAd(adRequestInter);
    }

    public void Toast(String str) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_view,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.toast);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(str);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void onclick() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MODE_SEARCH();
                EdittextSearch();
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                btn_close_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MODE_NOMAL();
                        if (radioObjects.size() > 0 && listviewAdapter != null) {
                            listviewAdapter.resetdata("");
                        }
                        editText_search.setText("");
                        imm.hideSoftInputFromWindow(editText_search.getWindowToken(), 0);
                    }
                });
            }
        });

        //onclick listview sations
        listViewRadio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flag = true;
                ((ListviewAdapter) listViewRadio.getAdapter()).selectedItem = General.getIntPreference(HomeActivity.this, "position");
                ((ListviewAdapter) listViewRadio.getAdapter()).notifyDataSetChanged();
                if (CheckConectNetwork.CheckConectNetwork(HomeActivity.this) == false) {
                    Toast("Check your network and restart application! Please");
                } else {
                    object = (RadioObject) parent.getAdapter().getItem(position);
                    General.saveStringPreference(HomeActivity.this, "Key_RadioName", object.name);
                    General.saveStringPreference(HomeActivity.this, "location", object.location);
                    General.saveStringPreference(HomeActivity.this, "url_station", object.link);
                    General.saveIntPreference(HomeActivity.this, "position", position);
                    btn_play_pause.setVisibility(View.GONE);

                    favoritesFlag = true;
                    for (int i = 0; i < favoriteObject.size(); i++) {
                        String url = favoriteObject.get(i).link;
                        if (url.equals(object.link)) {
                            favoritesFlag = false;
                            btn_add_favorite.setBackgroundResource(R.drawable.favorited);
                        } else {
                        }

                    }
                    if (favoritesFlag == true) {
                        btn_add_favorite.setBackgroundResource(R.drawable.add);
                    }

                    progressBar.setVisibility(View.VISIBLE);
                    textViewStation.setText(object.name);

                    ((ListviewAdapter) listViewRadio.getAdapter()).selectedItem = General.getIntPreference(HomeActivity.this, "position");
                    ((ListviewAdapter) listViewRadio.getAdapter()).notifyDataSetChanged();

                    ExoplayerRadio(HomeActivity.this, object.link);
                    imm.hideSoftInputFromWindow(editText_search.getWindowToken(), 0);

                    // showw  ad
                    avBanner.setVisibility(View.VISIBLE);
                    int count = General.getIntPreference(HomeActivity.this, "timeOnclick");
                    if (count < 2) {
                        General.saveIntPreference(HomeActivity.this, "timeOnclick", count + 1);
                    } else {
                        General.saveIntPreference(HomeActivity.this, "timeOnclick", 0);
                        showFullAds();
                    }
                }
            }
        });
        btn_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playercontrol == null) {
                } else {
                    if (playercontrol.isPlaying()) {
                        exoPlayer.setPlayWhenReady(false);
                        btn_play_pause.setBackgroundResource(R.drawable.play);
                        StopNotification.StopNotification(HomeActivity.this);

                    } else {

                        exoPlayer.setPlayWhenReady(true);
                        btn_play_pause.setBackgroundResource(R.drawable.pause);

                    }
                }
            }
        });

        //onclick add favorite
        btn_add_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (object == null) {
                    Toast("Select a station !");
                } else {
                    if (favoriteObject.size() == 0) {
                        favoriteObject.add(object);
                        btn_add_favorite.setBackgroundResource(R.drawable.favorited);
                        Toast("Added to your favorites !");
                    } else {
                        favoritesFlag = true;
                        for (int i = 0; i < favoriteObject.size(); i++) {
                            String url = favoriteObject.get(i).link;
                            if (url.equals(object.link)) {
                                favoritesFlag = false;
                                favoriteObject.remove(i);
                                Toast("Remove From Your Favorites !");
                                btn_add_favorite.setBackgroundResource(R.drawable.add);
                                if (resetFlag == true) {
                                    listviewAdapter.notifyListObjectChanged(favoriteObject);

                                }
                            } else {
                            }
                        }
                        if (favoritesFlag == true) {
                            favoriteObject.add(object);
                            Toast("Added to your favorites !");
                            btn_add_favorite.setBackgroundResource(R.drawable.favorited);
                            if (resetFlag == true) {
                                listviewAdapter.notifyListObjectChanged(favoriteObject);

                            }
                        }
                    }
                }
            }

        });

        //onclick menu
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitMenuFlag = true;
                resetFlag = false;
                TranslateAnimation anim = new TranslateAnimation(-500f, 0f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.VISIBLE);
                coverView.setVisibility(View.VISIBLE);

            }
        });
        // coverview onclick
        coverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coverView.setVisibility(View.GONE);
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);

            }
        });
        //conclick viewCountry
        viewCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearlayout_Listview_country.setVisibility(View.GONE);
                viewCountry.setVisibility(View.GONE);
                linearlayout_Listview_category.setVisibility(View.GONE);
            }
        });


// onclick Country

        linearlayout_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitListviewCoutry = true;
                viewCountry.setVisibility(View.VISIBLE);
                linearlayout_Listview_country.setVisibility(View.VISIBLE);

                CountryAdapter countryAdapter = new CountryAdapter(HomeActivity.this, ArrayCountry.ArrayCountry());
                listViewCountry.setAdapter(countryAdapter);
            }
        });

        // onclick listview of country
        listViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CountryObject countryObject = (CountryObject) parent.getAdapter().getItem(position);
                countryFlag = true;
                exitListviewCoutry = false;
                linearlayout_Listview_country.setVisibility(View.GONE);
                viewCountry.setVisibility(View.GONE);
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
                if (countryObject.flag != null) {
                    Picasso.with(HomeActivity.this)
                            .load(countryObject.flag)
                            .into(imageFlag);
                }
                if (countryObject.country != null) {
                    General.saveStringPreference(HomeActivity.this, "country", countryObject.country);
                    String string = countryObject.country.replace(" ", "").toLowerCase();
                    String str = countryObject.country;
                    String s = String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
                    textView_name_app.setText(s + " Radio");
                    As as = new As(HomeActivity.this);
                    as.execute(string);

                }

            }
        });
        // onclick Home
        linearLayout_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coverView.setVisibility(View.GONE);
                imageFlag.setImageResource(R.drawable.icon);
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);
                textView_name_app.setText(R.string.app_name);
                if (radioObjects.size() == 0) {
                    listviewAdapter = new ListviewAdapter(HomeActivity.this, GetArraylist.getAraylist(HomeActivity.this, "database"));
                    listViewRadio.setAdapter(listviewAdapter);
                } else {
                    listviewAdapter = new ListviewAdapter(HomeActivity.this, radioObjects);
                    listViewRadio.setAdapter(listviewAdapter);
                }

                countryFlag = false;
                exitMenuFlag = false;
                //  listviewAdapter.notifyListObjectChanged(radioObjects);
            }
        });
        // onclick favorite
        linearLayout_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (favoriteObject.size() == 0) {
                    Toast("No Favorite Station !");
                    linearLayout_menu.setVisibility(View.GONE);
                    textView_name_app.setText("Your Favorites ");
                } else {
                    textView_name_app.setText("Your Favorites");
                    resetFlag = true;
                }
//                listviewAdapter.notifyListObjectChanged(favoriteObject);
                listviewAdapter = new ListviewAdapter(HomeActivity.this, favoriteObject);
                listViewRadio.setAdapter(listviewAdapter);
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
            }
        });


        // onclick category
        linearlayout_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitListviewCategory = true;
                if (countryFlag == false) {
                    linearlayout_Listview_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapter = new ArrayAdapter(HomeActivity.this, R.layout.item_category, arrayListCategory);
                    listView_Category.setAdapter(adapter);
                    viewCountry.setVisibility(View.VISIBLE);

                    //onclick listview category
                    listView_Category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            arrayListCategoryObject.clear();
                            String str = arrayListCategory.get(position);
                            linearlayout_Listview_category.setVisibility(View.GONE);
                            viewCountry.setVisibility(View.GONE);
                            linearLayout_menu.setVisibility(View.GONE);
                            coverView.setVisibility(View.GONE);
                            for (int i = 0; i < radioObjects.size(); i++) {
                                if (str.equals("Other")) {
                                    if (radioObjects.get(i).category.equals("All")) {
                                        arrayListCategoryObject.add(radioObjects.get(i));
                                    }
                                } else {
                                    if (radioObjects.get(i).category.equals(str)) {
                                        arrayListCategoryObject.add(radioObjects.get(i));
                                    }
                                }
                            }
                            listviewAdapter = new ListviewAdapter(HomeActivity.this, arrayListCategoryObject);
                            listViewRadio.setAdapter(listviewAdapter);
                        }

                    });
                } else {
                    linearlayout_Listview_category.setVisibility(View.VISIBLE);
                    viewCountry.setVisibility(View.VISIBLE);
                }

            }
        });


        //onclick sleep timer
        linearLayout_menu_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitTimerFlag = true;
                coverView.setVisibility(View.GONE);
                timerView.setVisibility(View.VISIBLE);
                linearLayout_timer.setVisibility(View.VISIBLE);
                TimerAdapter tAdapter = new TimerAdapter(HomeActivity.this, ArrayTimer.ArrayTimer());
                listViewTimer.setAdapter(tAdapter);
            }
        });
        // onclick close_timer
        btn_close_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitMenuFlag = false;
                TranslateAnimation anim = new TranslateAnimation(0f, -800f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_timer.setAnimation(anim);
                linearLayout_timer.setVisibility(View.GONE);
                timerView.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
                linearLayout_menu.setVisibility(View.GONE);
            }
        });
        //oncllick rate app
        linearLayout_rate_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitMenuFlag = false;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                coverView.setVisibility(View.GONE);
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);

            }
        });
        textViewRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            }
        });
        //onclick share app
        linearLayout_share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitMenuFlag = false;
                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Radio");
                    String sAux = "\nLet me recommend you this application\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=" + getPackageName();
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
        //onclick privacy policy
        linearLayout_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TranslateAnimation anim = new TranslateAnimation(0f, -650f, 0f, 0f);  // might need to review the docs
                anim.setDuration(300); // set how long you want the animation
                exitMenuFlag = false;
                linearLayout_menu.setAnimation(anim);
                linearLayout_menu.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("By LFCorp")
                        .setMessage(
                                R.string.privacy_policy)

                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setTextColor(R.color.colorPrimary);

            }
        });

        // onclick listview timer
        listViewTimer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                TimerObject timerObject = (TimerObject) parent.getAdapter().getItem(position);
                CountdownTimer.CountdownTimer(HomeActivity.this, timerObject.time, textViewCountdownTimer, textViewCountdownTimer1);
            }
        });

        //onclick stop timer
        btn_stop_countdown_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                    textViewCountdownTimer.setText("");
                    textViewCountdownTimer1.setText("");
                }

            }
        });

        // onclick Exit
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.super.onBackPressed();
            }
        });

        // onclick cancel
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutExit.setVisibility(View.GONE);
                timerView.setVisibility(View.GONE);
                coverView.setVisibility(View.GONE);
                linearLayout_menu.setVisibility(View.GONE);

            }
        });
    }


    public class ASData extends AsyncTask<Object, Integer, ArrayList<RadioObject>> {
        @Override
        protected void onPreExecute() {
           progressBarBegin.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<RadioObject> doInBackground(Object... params) {
            GetData.GetData(radioObjects, arrayListCategory);
            return radioObjects;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<RadioObject> s) {

            progressBarBegin.setVisibility(View.GONE);
            SaveArraylist.saveArraylist(HomeActivity.this, "database", radioObjects);
            listviewAdapter = new ListviewAdapter(HomeActivity.this, radioObjects);
            listViewRadio.setAdapter(listviewAdapter);
        }
    }

    public void EdittextSearch() {
        editText_search = (EditText) findViewById(R.id.edt_search);
        editText_search.requestFocus();
        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (radioObjects.size() > 0 && listviewAdapter != null) {
                    String textSearch = editText_search.getText().toString().toLowerCase().trim();
                    listviewAdapter.resetdata(textSearch);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void ExoplayerRadio(Context context, String url) {

        Uri radioUri = Uri.parse(url);
        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
        String userAgent = Util.getUserAgent(context, "Radio/w1");
        DefaultUriDataSource dataSource = new DefaultUriDataSource(context, null, userAgent);
        ExtractorSampleSource sampleSource = new ExtractorSampleSource(
                radioUri, dataSource, allocator, BUFFER_SEGMENT_SIZE * BUFFER_SEGMENT_COUNT);
        MediaCodecAudioTrackRenderer audioRenderer = new MediaCodecAudioTrackRenderer(sampleSource);
        exoPlayer.prepare(audioRenderer);
        exoPlayer.setPlayWhenReady(true);
        playercontrol = new PlayerControl(exoPlayer);
        exoPlayer.addListener(new ExoPlayer.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (exoPlayer.getPlaybackState() == ExoPlayer.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                    btn_play_pause.setVisibility(View.VISIBLE);
                    btn_play_pause.setBackgroundResource(R.drawable.pause);
                    textViewStation.setText(object.name);
                    startNotification.startNotification(HomeActivity.this);
                }
            }

            @Override
            public void onPlayWhenReadyCommitted() {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                progressBar.setVisibility(View.GONE);
                textViewStation.setText("Sorry, this radio is offline for now");
            }
        });
    }


    // pause player on incoming call
    PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == TelephonyManager.CALL_STATE_RINGING) {
                //INCOMING call
                //do all necessary action to pause the audio
                if (playercontrol != null) {//check mp
                    if (playercontrol.isPlaying()) {
                        phoneStateListenerFlag = true;
                        exoPlayer.setPlayWhenReady(false);
                        btn_play_pause.setBackgroundResource(R.drawable.play);
                    }
                }

            } else if (state == TelephonyManager.CALL_STATE_IDLE) {
                //Not IN CALL
                //do anything if the phone-state is idle
                if (playercontrol != null) {//check mp
                    if (phoneStateListenerFlag = true) {
                        btn_play_pause.setBackgroundResource(R.drawable.pause);
                        phoneStateListenerFlag = false;
                        exoPlayer.setPlayWhenReady(true);
                    }

                }
            } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                //A call is dialing, active or on hold
                //do all necessary action to pause the audio
                //do something here
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    @Override
    public void onBackPressed() {

        if (exitFlag == true) {
            relativeLayoutExit.setVisibility(View.GONE);
            exitFlag = false;
            timerView.setVisibility(View.GONE);
        } else {
            if (exitMenuFlag == false) {
                SaveArraylist.saveArraylist(HomeActivity.this, "favorite", favoriteObject);
                relativeLayoutExit.setVisibility(View.VISIBLE);
                exitFlag = true;
                timerView.setVisibility(View.VISIBLE);

            } else {
                if (exitListviewCoutry == true) {
                    linearlayout_Listview_country.setVisibility(View.GONE);
                    viewCountry.setVisibility(View.GONE);
                    exitListviewCoutry = false;
                } else {
                    if (exitListviewCategory == true) {
                        linearlayout_Listview_category.setVisibility(View.GONE);
                        viewCountry.setVisibility(View.GONE);
                        exitListviewCategory = false;

                    } else {
                        if (exitTimerFlag == true) {
                            linearLayout_timer.setVisibility(View.GONE);
                            timerView.setVisibility(View.GONE);
                            exitTimerFlag = false;
                        } else {
                            TranslateAnimation anim = new TranslateAnimation(0f, -700f, 0f, 0f);  // might need to review the docs
                            anim.setDuration(250); // set how long you want the animation
                            linearLayout_menu.setAnimation(anim);
                            linearLayout_menu.setVisibility(View.GONE);
                            coverView.setVisibility(View.GONE);
                            viewCountry.setVisibility(View.GONE);
                            exitMenuFlag = false;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        SaveArraylist.saveArraylist(HomeActivity.this, "favorite", favoriteObject);
        SaveArraylist.saveArraylist(HomeActivity.this, "radioobject", radioObjects);
        super.onStop();
    }


}
