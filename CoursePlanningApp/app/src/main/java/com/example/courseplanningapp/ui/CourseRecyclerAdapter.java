package com.example.courseplanningapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.courseplanningapp.constants.Constants;
import com.example.courseplanningapp.R;
import com.example.courseplanningapp.model.Course;
import com.example.courseplanningapp.model.CourseManager;


/*
    Referenced how to set up RecyclerViews from my CMPT 276 group project
    https://csil-git1.cs.surrey.sfu.ca/276-20201-Helium/prj
*/
public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {

    private Context context;
    private CourseManager courseManager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CourseRecyclerAdapter(Context context) {
        this.context = context;
        courseManager = CourseManager.getInstance(context);
    }


    @NonNull
    @Override
    public CourseRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.course_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRecyclerAdapter.ViewHolder holder, final int position) {

        final Course course = courseManager.getFilteredCourses().get(position);

        // init views
        TextView year = holder.layout.findViewById(R.id.year);
        TextView semester = holder.layout.findViewById(R.id.semester);
        TextView subject = holder.layout.findViewById(R.id.subject);
        TextView courseNumber = holder.layout.findViewById(R.id.course_number);
        TextView title = holder.layout.findViewById(R.id.title);
//        TextView w = holder.layout.findViewById(R.id.w);
//        TextView q = holder.layout.findViewById(R.id.q);
//        TextView bhum = holder.layout.findViewById(R.id.bhum);
//        TextView bsoc = holder.layout.findViewById(R.id.bsoc);
//        TextView bsci = holder.layout.findViewById(R.id.bsci);
        ImageButton removeBtn = holder.layout.findViewById(R.id.removeBtn);

        // set values to each view
        year.setText(course.getYear());
        semester.setText(course.getSemester());
        subject.setText(course.getSubject());
        courseNumber.setText(course.getCourseNumber());
        title.setText(course.getTitle());

        // set if the course is WQB course, if it is, set the text, otherwise set nothing
//        if(course.getW()){ w.setText("W"); }else{ w.setText(""); }
//        if(course.getQ()){ q.setText("Q"); }else{ q.setText(""); }
//        if(course.getbHum()){ bhum.setText("B-Hum"); }else{ bhum.setText(""); }
//        if(course.getbSoc()){ bsoc.setText("B-Soc"); }else{ bsoc.setText(""); }
//        if(course.getbSci()){ bsci.setText("B-Sci"); }else{ bsci.setText(""); }

        // set the remove button
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemRemoved(position);
                courseManager.removeCourse(course);
                CourseList.recyclerAdapter.notifyDataSetChanged();
            }
        });

    }




    @Override
    public int getItemCount() {
        return courseManager.getFilteredCourses().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView;
        }
    }
}
