package examples.instagram.volley.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import examples.instagram.volley.R;
import examples.instagram.volley.instagram.InstagramAdapter;
import examples.instagram.volley.instagram.rest.EndPointListener;
import examples.instagram.volley.instagram.rest.EndPointResult;
import examples.instagram.volley.instagram.rest.tag.media.Media;


public class PhotoList extends Activity implements EndPointListener, AbsListView.OnScrollListener{
    private Media media;
    private InstagramAdapter instagramAdapter;
    private ListView listView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        getComponents();
        setAdapter();
        addProgressBar();
    }

    private void addProgressBar(){
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setIndeterminate(true);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 24));

        final FrameLayout decorView = (FrameLayout) getWindow().getDecorView();
        decorView.addView(progressBar);

        ViewTreeObserver observer = progressBar.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View contentView = decorView.findViewById(android.R.id.content);
                progressBar.setY(contentView.getY() - 15);

                ViewTreeObserver observer = progressBar.getViewTreeObserver();
                observer.removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void getComponents(){
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnScrollListener(this);
    }

    private void setAdapter(){
        if(instagramAdapter == null){
            instagramAdapter = new InstagramAdapter( media = new Media("selfie"), this );
            media.addListener(this);
        }
        listView.setAdapter(instagramAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.photo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        instagramAdapter.dispose();
        media.removeListener(this);
    }

    @Override
    public void onNetworkOffline() {
        Toast.makeText(this, "Network offline, check your connection", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStartLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMoreDataSuccess(EndPointResult endPointResult) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadingMoreDataFail(Exception exception) {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onInstagramNotResponding() {
        Toast.makeText(this, "Instagram not responding", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {}

    @Override
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if(instagramAdapter == null) return;
        if(i + 1 >= i3 - i2) instagramAdapter.loadMoreData();
    }
}
