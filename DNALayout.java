package com.comcast.freeflow.examples.freeflowphotogrid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Rect;
import android.util.Log;

import com.comcast.freeflow.core.FreeFlowItem;
import com.comcast.freeflow.core.Section;
import com.comcast.freeflow.layouts.FreeFlowLayoutBase;
import com.comcast.freeflow.utils.ViewUtils;

public class DNALayout extends FreeFlowLayoutBase {

	public String TAG = "DNALayout";
	private HashMap<Object, FreeFlowItem> map;
	private Section s;

	private int itemHeight = 250;

	public DNALayout() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<Object, FreeFlowItem> getItemProxies(int viewPortLeft,
			int viewPortTop) {
		Rect viewport = new Rect(viewPortLeft, viewPortTop, viewPortLeft
				+ width, viewPortTop + height);
		HashMap<Object, FreeFlowItem> ret = new HashMap<Object, FreeFlowItem>();

		Iterator<Entry<Object, FreeFlowItem>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, FreeFlowItem> pairs = it.next();
			FreeFlowItem p = (FreeFlowItem) pairs.getValue();
			if (Rect.intersects(p.frame, viewport)) {
				ret.put(pairs.getKey(), p);
			}
		}
		return ret;

	}

	@Override
	public void setLayoutParams(FreeFlowLayoutParams params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepareLayout() {
		Log.d(TAG, "prepare layout!!!");
		map = new HashMap<Object, FreeFlowItem>();
		s = itemsAdapter.getSection(0);
		Log.d(TAG, "prepare layout for: " + s.getDataCount());
		for (int i = 0; i < s.getDataCount(); i++) {

			FreeFlowItem p = new FreeFlowItem();
			p.isHeader = false;
			p.itemIndex = i;
			p.itemSection = 0;
			p.data = s.getDataAtIndex(i);

			Rect r = new Rect();

			switch (i % 6) {
			case (0):
				r.left = 0;
				r.right = width;
				r.top = i / 6 * (4 * itemHeight);
				r.bottom = r.top + itemHeight;

				break;

			case (1):
				r.left = 0;
				r.right = width / 2;
				r.top = i / 6 * (4 * itemHeight) + itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			case (2):
				r.left = width / 2;
				r.right = width;
				r.top = i / 6 * (4 * itemHeight) + itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			case (3):
				r.left = 0;
				r.right = width / 2;
				r.top = i / 6 * (4 * itemHeight) + 2 * itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			case (4):

				r.left = width / 2;
				r.right = width;
				r.top = i / 6 * (4 * itemHeight) + 2 * itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			case (5):
				r.left = 0;
				r.right = width;
				r.top = i / 6 * (4 * itemHeight) + 3 * itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			case (6):

				r.left = width / 2;
				r.right = width;
				r.top = i / 6 * (4 * itemHeight) + 4 * itemHeight;
				r.bottom = r.top + itemHeight;

				break;

			default:
				break;
			}
			p.frame = r;
			map.put(s.getDataAtIndex(i), p);
		}
	}

	@Override
	public FreeFlowItem getFreeFlowItemForItem(Object item) {
		return map.get(item);
	}

	@Override
	public boolean horizontalScrollEnabled() {
		return false;
	}

	@Override
	public boolean verticalScrollEnabled() {
		return true;
	}

	@Override
	public int getContentWidth() {
		return 0;
	}

	@Override
	public int getContentHeight() {
		
		int additional = 0;
		if((s.getDataCount() * 4) % 6 != 0){
			additional = 1;
		}
		
		int val =  ((s.getDataCount() * 4 / 6 )+additional) *itemHeight;
		Log.d(TAG, "items: "+s.getDataCount()+"/  "+itemHeight+": "+val);
		
		return val;
	}

	@Override
	public FreeFlowItem getItemAt(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
