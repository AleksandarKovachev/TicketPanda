package sofia.tu.panda.ticket.ticketpanda.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sofia.tu.panda.ticket.ticketpanda.R;

public class SwipePageAdapter extends PagerAdapter {

    private Context context;
    private int[] images;
    private LayoutInflater layoutInflater;

    public SwipePageAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.view_pager_swipe_view, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.swipe_image_view);
        imageView.setImageResource(images[position]);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
