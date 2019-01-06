package camp.codelab.daragati

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val firebaseReference = FirebaseDatabase.getInstance().getReference("student")
        firebaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val student = snapshot.getValue(Student::class.java)
                student?.let { student ->
                    studentNameTextView.text = student.studentName
                    firstSemesterTextView.text = student.firstSemester.toString()
                    secondSemesterTextView.text=student.secondSemester.toString()
                    midYearTextView.text=student.midYear.toString()
                    finalExamTextView.text=student.finalExam.toString()
                    finalResultTextView.text=student.finalResult.toString()
                }
            }


        })

    }
}
