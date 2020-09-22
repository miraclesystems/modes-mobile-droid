package mil.dod.mcfp.mymilitaryonesource.kotlin.MVVM.Utils

import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView


//JS For topics so height grows based off content

object DynamicListHeight {


    /*
    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter: ListAdapter = listView.getAdapter()
            ?:
            return
        var totalHeight = 0
        val desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.AT_MOST)
        for (i in 0 until listAdapter.getCount()) {
            val listItem: View = listAdapter.getView(i, null, listView)
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.getMeasuredHeight()
        }
        val params: ViewGroup.LayoutParams = listView.getLayoutParams()
        params.height = totalHeight + listView.getDividerHeight() * (listAdapter.getCount() - 1)
        listView.setLayoutParams(params)
        listView.requestLayout()
    }

*/
    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter: ListAdapter = listView.getAdapter()
            ?: //do nothing return null
            return
        //set listAdapter in loop for getting final size
        var totalHeight = 0
        for (size in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(size, null, listView)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        //setting listview item in adapter
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)

        listView.layoutParams = params

    }



    fun setGridViewHeightBasedOnChildren(gridView: GridView) {
        val listAdapter: ListAdapter = gridView.getAdapter()
            ?:
            return
        var totalHeight = 0
        val desiredWidth = MeasureSpec.makeMeasureSpec(gridView.getWidth(), MeasureSpec.AT_MOST)
        for (i in 0 until listAdapter.getCount()) {
            val listItem: View = listAdapter.getView(i, null, gridView)
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.getMeasuredHeight()
        }


        Log.d("grid count", listAdapter.getCount().toString())

        val params: ViewGroup.LayoutParams = gridView.getLayoutParams()

        if (listAdapter.getCount() > 3) {

            params.height = (totalHeight / 2) + 320
            gridView.setPadding(0, 0, 0, 10)

            //params.height = totalHeight + gridView.columnWidth * (listAdapter.getCount() - 1)

            gridView.setLayoutParams(params)
            gridView.requestLayout()

        } else {

            gridView.requestLayout()

        }
    }




}