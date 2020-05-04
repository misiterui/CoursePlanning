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

import java.io.IOException;


/*
    Referenced how to set up RecyclerViews from my CMPT 276 group project
    https://csil-git1.cs.surrey.sfu.ca/276-20201-Helium/prj
*/
public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {

    private Context context;
    private CourseManager courseManager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CourseRecyclerAdapter(Context context, String major, String startYear, String startSemester, int courseCount) {
        this.context = context;
        courseManager = CourseManager.getInstance(context, major, startYear, startSemester, courseCount);
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
        ImageButton removeBtn = holder.layout.findViewById(R.id.removeBtn);

        // set values to each view
        year.setText(course.getYear());
        semester.setText(course.getSemester());
        subject.setText(course.getSubject());
        courseNumber.setText(course.getCourseNumber());
        title.setText(course.getTitle());

        // set the remove button
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                notifyItemRemoved(position);
                courseManager.removeCourseIdFromSharedPreference(context, course.getCourseId());
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

    public void resort() {
        courseManager.resort();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadCmptData(String startYear, String startSemester, int courseCount) throws IOException {
        courseManager.readCmptData(context, startYear, startSemester, courseCount);
    }

}
