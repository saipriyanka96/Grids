package com.example.layout.grids;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creating myadapter object
        MyAdapter adp=new MyAdapter(MainActivity.this);
        gridView=(GridView)findViewById(R.id.mygridview);
        gridView.setAdapter(adp);
        //Sets the data behind this ListView.
    }
    class MyAdapter extends BaseAdapter {
        //BaseAdapter:Common base class of common implementation for an Adapter that can be used in both ListView (by implementing
        // the specialized ListAdapter interface) and Spinner (by implementing the specialized SpinnerAdapter interface).

        ArrayList<MyClass> list;
        Context context;
        //Context:Interface to global information about an application environment
        MyAdapter(Context context){
            this.context=context;//refering to current object
            list= new ArrayList<MyClass>();//list will be taken from MyClass
            Resources res= context.getResources();
            //The Android resource system keeps track of all non-code assets associated with an application.
            //arraylist of images
            int[] images={R.drawable.gingerbread,R.drawable.honeycomb,R.drawable.icecream,R.drawable.jellybean,
                    R.drawable.kitkat,R.drawable.lollipop};
            //Now putting it in main arraylist using for loop
            for(int i=0;i<6;i++){
                MyClass myClass= new MyClass(images[i]);
                list.add(myClass);
                //creating loop for maintainind the images in order
            }

        }
        @Override
        public int getCount() {
            return list.size();//to get list size of the image
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }//to set the position

        @Override
        public long getItemId(int position) {
            return position;
        }//to return the id of the image
        class ViewHolder{
            ImageView myimage;

            ViewHolder(View view){
                myimage= (ImageView) view.findViewById(R.id.imageView);

            }
        }
        public class MyClass {
            int versionImage;

//version of the image to be taken that we write separate class and represent that to the current image
            public MyClass(int versionImage) {
                this.versionImage = versionImage;

            }


        }

        //this method is responsible for creating views
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View row= view;
            ViewHolder holder=null;
            //A ViewHolder object stores each of the component views inside the tag field of the Layout, so you can
            // immediately access them without the need to look them up repeatedly.
            if(row==null) {
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                //Instantiates a layout XML file into its corresponding View objects
                row= inflater.inflate(R.layout.single_view,parent,false);
                // //inflater.inflate is used to create the view from our xml file
                holder =new ViewHolder(row);
                //here holder will holds the  values in the row
                row.setTag(holder);// store the holder with the view.

            }else{
                // we've just avoided calling findViewById() on resource everytime

                // just use the viewHolder

                holder= (ViewHolder) row.getTag();

            }
            //we get the value of list position
            MyClass temp= list.get(position);
            MyClass temp1=list.get(position);
            //if we want to change image from any place in our code where we do not have context to call getResources() method on it.
            // e.g in Adapters. No need to pass context through constructor just for this funcionality
            holder.myimage.setImageResource(temp.versionImage);

            return row;
        }
    }
}
