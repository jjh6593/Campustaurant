package com.example.campustaurant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuSelectAdapter2 extends RecyclerView.Adapter<MenuSelectAdapter2.CustomViewHoler> { // CustomViewHolder는 직접 만들어줘야함

    private ArrayList<Menu> menuArrayList;

    public MenuSelectAdapter2(ArrayList<Menu> menuArrayList) { // Constructer(생성자)
        this.menuArrayList = menuArrayList; // MenuSelectActivity2에서 생성된 menuArrayList로 초기화
    }

    @NonNull
    @Override
    public MenuSelectAdapter2.CustomViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 생명주기 생성단계에서 한번 실행되는 메서드

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list2, parent, false); // food_list2 레이아웃을 inflate해서 view 객체로 가져옴
        CustomViewHoler holder = new CustomViewHoler(view); // 만든 View객체 view를 전달하여 CustomViewHolder 객체 holder 생성


        return holder; // onBindViewHolder에서 holder를 인자로 받기 때문에 리턴해줘야함
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSelectAdapter2.CustomViewHoler holder, int position) { // MenuSelectAdapter2.CustomViewHoler에서 생성된 holder를 받아옴
        holder.tvFood.setText(menuArrayList.get(position).getFood());
        // Menu에 저장된 멤버변수들 값으로 text를 세팅

        holder.itemView.setTag(position); // itemView의 position(위치)값을 가져옴
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // food_list2의 각 LinearLayout을 짧게 누르면 발생
                String curName = holder.tvFood.getText().toString(); // holder의 tvFood으로부터 text를 가져옴
                Toast.makeText(view.getContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) { // food_list2의 각 LinearLayout을 길게 누르면 발생
                remove(holder.getAdapterPosition()); // 클릭한 LinearLayout 삭제
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != menuArrayList ? menuArrayList.size() : 0);
    }

    public void remove(int position){ // 직접 구현한 메서드
        try{
            menuArrayList.remove(position); // position(클릭한 LinearLayout 위치)에 해당하는 LinearLayout 삭제
            notifyItemRemoved(position); // 새로고침 해줘야 remove 반영됨
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHoler extends RecyclerView.ViewHolder {
        protected TextView tvFood;

        public CustomViewHoler(@NonNull View itemView) { // Constructer(생성자) // 인자: 위에서 만든 View객체 view
            super(itemView);
            this.tvFood = (TextView) itemView.findViewById(R.id.tv_food);
        }
    }
}
