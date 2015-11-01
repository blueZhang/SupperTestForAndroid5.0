package com.bluezhang.supperapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.LinkedList;
import java.util.List;

public class Android5Activity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private List<String> data;
    private MyRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android5);
        data = new LinkedList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycleview_id_android5);
        /////--------------------------------
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);


        /////--------------------------------

        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new MyRecycleAdapter(data);
        recyclerView.setAdapter(adapter);
        //    recyclerView.addOnItemTouchListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    /**********
     * 添加数据 点击函数
     * 并且更新
     *
     * @param view
     */
    public void add_item_data(View view) {
        data.add(0, "添加的----" + System.currentTimeMillis());
        adapter.notifyDataSetChanged();
    }

    public void delete_item_data(View view) {
        if (!data.isEmpty()){
            data.remove(0);
        }
        adapter.notifyItemRemoved(0);
    }

    /*********
     * 继承 特定的adapter  泛型为 viewhoulder
     */
    private class MyRecycleAdapter extends RecyclerView.Adapter<MViewHolder> {
        /*********
         * 根据viewtype 创建 加载特定的布局 来创建 加载好的布局
         * 交给viewholder 创建新的 viewholde 对象 并且返回
         * 因为recycleview  子啊 viewhoulder 为null 的情况才会调用这个方法
         * 如果 viewhoulder 以及存在 呢么不会进入这个方法
         * 方法 本身只负责 holder 的创建 不处理数据
         *
         * @param parent
         * @param viewType
         * @return 不用检查复用
         */
        private List<String> mdata;

        public MyRecycleAdapter(List<String> data) {
            this.mdata = data;
        }

        @Override
        public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MViewHolder holder = null;
            LayoutInflater inflater = LayoutInflater.from(Android5Activity.this);
            View view = inflater.inflate(R.layout.item, parent, false);
            //  holder = new MViewHolder(view,Android5Activity.this);
            holder = new MViewHolder(view);
            return holder;
        }

        /********
         * 用于显示指定位置的数据的内容 ，通过viewholder来进行相应控件的查找更新
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(MViewHolder holder, int position) {
            holder.textTitle.setText("--" + mdata.get(position) + "---个");
            int m = position % 7;
            switch (m) {
                case 1:
                    holder.imageVIcon.setImageResource(R.mipmap.a);
                    break;
                case 2:
                    holder.imageVIcon.setImageResource(R.mipmap.b);
                    break;
                case 3:
                    holder.imageVIcon.setImageResource(R.mipmap.c);
                    break;
                case 4:
                    holder.imageVIcon.setImageResource(R.mipmap.d);
                    break;
                case 5:
                    holder.imageVIcon.setImageResource(R.mipmap.e);
                    break;
                case 6:
                    holder.imageVIcon.setImageResource(R.mipmap.f);
                    break;
                case 0:
                    holder.imageVIcon.setImageResource(R.mipmap.g);
                    break;
            }


        }

        /******
         * 获取总数
         *
         * @return
         */
        @Override
        public int getItemCount() {
            int ret = 0;
            if (mdata != null) {
                ret = mdata.size();
            }
            return ret;
        }
    }

    /**********
     * c创建 viewholder
     */
    private static class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageVIcon;
        public TextView textTitle;

        public MViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);
            //通常构造方法 用于获取控件
            imageVIcon = (ImageView) itemView.findViewById(R.id.item_iamge_icon_id);
            textTitle = (TextView) itemView.findViewById(R.id.item_title_id);

            //--------后续处理点击事件的操作
            //  itemView.setOnClickListener(listener);
            itemView.setOnClickListener(this);

        }

        public MViewHolder(View itemView) {
            super(itemView);
            //通常构造方法 用于获取控件
            imageVIcon = (ImageView) itemView.findViewById(R.id.item_iamge_icon_id);
            textTitle = (TextView) itemView.findViewById(R.id.item_title_id);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Context context = itemView.getContext();
            Toast.makeText(context, "点击--" + position, Toast.LENGTH_SHORT).show();

        }
    }

}
