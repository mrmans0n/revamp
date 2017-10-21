package io.nlopez.revamp.sample.binders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.core.Binder;
import drebin.core.ViewFactory;
import drebin.core.ViewHost;
import io.nlopez.revamp.sample.R;
import io.nlopez.revamp.sample.model.Place;

public class PlaceBinder implements Binder<LinearLayout, PlaceBinder.PlaceViewHost, Place, ClickableElementEnvironment<Place>> {

  @Override
  public ViewFactory<LinearLayout> getViewFactory() {
    return ViewFactory.INFLATE.fromLayout(R.layout.view_place);
  }

  @Override
  public PlaceViewHost createViewHost(LinearLayout view) {
    return new PlaceViewHost(view);
  }

  @Override
  public void bind(final Place place, PlaceViewHost placeViewHost, final ClickableElementEnvironment<Place> binderEnvironment) {
    Picasso.with(placeViewHost.rootView.getContext()).load(place.getImageUrl()).into(placeViewHost.placeImage);
    placeViewHost.placeText.setText(place.getName());

    placeViewHost.rootView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        binderEnvironment.performClick(v, place);
      }
    });
  }

  @Override
  public void unbind(PlaceViewHost placeViewHost, ClickableElementEnvironment<Place> binderEnvironment) {
    placeViewHost.rootView.setOnClickListener(null);
  }

  static class PlaceViewHost extends ViewHost<LinearLayout> {
    @BindView(R.id.place_image)
    ImageView placeImage;

    @BindView(R.id.place_text)
    TextView placeText;

    public PlaceViewHost(LinearLayout view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }
}
