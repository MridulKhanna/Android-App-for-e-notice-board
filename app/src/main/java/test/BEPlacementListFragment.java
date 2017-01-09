package com.example.hp.pictconnect.test;

import android.support.v4.app.Fragment;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.TextView;

        import com.example.hp.pictconnect.NoticeLab;
        import com.example.hp.pictconnect.NoticePagerActivity;
        import com.example.hp.pictconnect.Notice_info;
        import com.example.hp.pictconnect.R;

import java.util.ArrayList;
import java.util.List;

public class BEPlacementListFragment extends Fragment {
    private RecyclerView mNoticeRecyclerView;
    private NoticeAdapter mAdapter;

    List<Notice_info> notices,copyNotices;


    //-----------------------------------------------
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_notice_list,container,false);       // a default layout with a RecyclerView is defined
        mNoticeRecyclerView=(RecyclerView)view.findViewById(R.id.notice_recycler_view);   //the id of that RecyclerView
        mNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        UpdateUI();
        return view;
    }
    //------------------------------------------------------
    public void UpdateUI(){

        notices= NoticeLab.getNotices();     //all original notices
        copyNotices=new ArrayList<>();

        for (Notice_info notice : notices) {           //enhanced for loop without indices..notice here is variable used of Notice_info data type..operating on mNotice_info

            if (notice.getIsPlacement()==1) {
                copyNotices.add(notice);
            }
        }

        if(mAdapter==null) {                        //running for the first time

            mAdapter = new BEPlacementListFragment.NoticeAdapter(copyNotices);
            mNoticeRecyclerView.setAdapter(mAdapter);
        }
        else mAdapter.notifyDataSetChanged();

    }
    //------------------------------------------------------------------------------------------------

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    // ViewHolders belong to the adapter. they work together
    // Adapters should feel free to use their own custom ViewHolder implementations to store data that makes binding view contents easier.
    public  class NoticeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView, mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Notice_info mNotice;

        public NoticeHolder(View itemview){
            super(itemview);                 //calls CrimeHolder constr.
            itemview.setOnClickListener(this);
            mTitleTextView=(TextView)itemview.findViewById(R.id.title_textbox);
            mDateTextView=(TextView)itemview.findViewById(R.id.date_textbox);
            mSolvedCheckBox=(CheckBox)itemview.findViewById(R.id.checkbox);
        }

        public void bindNotice(Notice_info notice){         //called in adapter
            mNotice=notice;
            mTitleTextView.setText(mNotice.getTitle());
            mDateTextView.setText(mNotice.getDate().toString());
        }

        //getActivity() is used in fragments to get the context of the activity in which they are inserted or inflated.
        //when user clicks on a crime...goto CrimeActivity class...create a new activity, listing the crimes details
        @Override
        public void onClick(View view) {
            Intent intent= NoticePagerActivity.newIntent(getActivity(),mNotice.getId());      //create a new intent by tracing the id
            startActivity(intent);
        }
    }
    //-------------------------------------------------------------------------------------
    //Adapters provide a binding from an app-specific data set to views that are displayed within a RecyclerView.

    public class NoticeAdapter extends RecyclerView.Adapter<NoticeHolder>{        //these three methods are must
        private List<Notice_info> mNotices;
        public NoticeAdapter(List<Notice_info> notices){
            mNotices=notices;
        }       //constructor

        //check return type
        //Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
        //This new ViewHolder should be constructed with a new View that can represent the items of the given type.
        // You can either create a new View manually or inflate it from an XML layout file.
        @Override
        public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());           //set a layout via layout manager
            View view=layoutInflater.inflate(R.layout.list_item_notices,parent,false);    //list_item_view contains the layout of each notice ie notice title,chkbox and date
            return new NoticeHolder(view);
        }

        //Called by RecyclerView to display the data at the specified position.
        //This method should update the contents of the itemView to reflect the item at the given position.
        @Override
        public void onBindViewHolder(NoticeHolder holder, int position) {
            Notice_info notice=mNotices.get(position);
            holder.bindNotice(notice);        //set the values for each notice row
        }

        //Returns the total number of items in the data set held by the adapter.
        @Override
        public int getItemCount() {
            return mNotices.size();
        }
    }
//----------------------------------------------------------------------------------

}
