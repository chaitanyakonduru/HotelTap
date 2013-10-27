package com.example.hoteltap;

import java.util.List;
import java.util.Random;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SlideShowFragment extends Fragment {

	private ImageView imageView;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private static SlideShowFragment slideShowFragment;
	
	public static SlideShowFragment newInstance()
	{
		if(slideShowFragment==null)
		{
			slideShowFragment=new SlideShowFragment();
		}
		return slideShowFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		imageView = new ImageView(getActivity());
		return imageView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageLoader=ImageLoader.getInstance();
		
	}
	
	
	public void updateUI(List<String> urls)
	{
		Random random=new Random();
	}
	private void configureBitmapOptions()
	{
		options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheOnDisc(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
	}
}
