package info.androidhive.tabsswipe.adapter;



import info.androidhive.tabsswipe.adapter.NewLoc_Fragment;
import info.androidhive.tabsswipe.adapter.SavedLoc_Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new NewLoc_Fragment();
		case 1:
			// Games fragment activity
			return new SavedLoc_Fragment();
		
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
