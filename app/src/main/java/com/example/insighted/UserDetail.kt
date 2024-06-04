package com.example.insighted


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.insighted.View.HomeFragment
import com.example.insighted.View.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetail : AppCompatActivity() {

    private lateinit var spinnerProvince: Spinner
    private lateinit var spinnerCity: Spinner
    private lateinit var progressBar: ProgressBar
    lateinit var emailET: EditText
    lateinit var firstNameET: EditText
    lateinit var lastNameET: EditText
    lateinit var tlpET: EditText
    lateinit var login: Button
    private lateinit var spinnerDistrict: Spinner
    private lateinit var db: FirebaseFirestore
    private lateinit var genderRadioGroup: RadioGroup
    lateinit var alertfirstnameTV: TextView
    lateinit var alertlastnameTV: TextView
    lateinit var alerttlpTV: TextView
    lateinit var alertgenderTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        spinnerProvince = findViewById(R.id.provinsi)
        spinnerCity = findViewById(R.id.kota)
        spinnerDistrict = findViewById(R.id.kecamatan)
        emailET = findViewById(R.id.email)
        firstNameET = findViewById(R.id.first_name)
        lastNameET = findViewById(R.id.last_name)
        tlpET = findViewById(R.id.Tlp)
        login = findViewById(R.id.save)
        progressBar = findViewById(R.id.progress_bar)
        genderRadioGroup = findViewById(R.id.gender)
        alertfirstnameTV = findViewById(R.id.alertfirstname)
        alertlastnameTV = findViewById(R.id.alertlastname)
        alerttlpTV = findViewById(R.id.alerttlp)
        alertgenderTV = findViewById(R.id.alertgender)

        emailET.isFocusableInTouchMode = false

        db = FirebaseFirestore.getInstance()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            updateUI(account)
        }

        fetchProvinces()

        login.setOnClickListener {
            saveUserData()
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            val email = account.email
            emailET.setText(email)
        } else {
            emailET.setText("")
        }
    }

    private fun fetchProvinces() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.getProvinces("eb21f3367d08d4ea4c6052fe2c5e4432203f67b51fee14ae7661db0d213de50f")

        call.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                if (response.isSuccessful) {
                    val provinces = response.body()?.value ?: emptyList()
                    Log.d("UserDetail", "Provinces fetched: ${provinces.size}")
                    setupProvinceSpinner(provinces)
                } else {
                    Log.e("UserDetail", "Failed to fetch data: ${response.errorBody()?.string()}")
                    showToast("Failed to fetch data")
                }
            }

            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                Log.e("UserDetail", "Error fetching data: ${t.message}")
                showToast("Error: ${t.message}")
            }
        })
    }

    private fun fetchCities(provinceId: String) {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.getCities("eb21f3367d08d4ea4c6052fe2c5e4432203f67b51fee14ae7661db0d213de50f", provinceId)

        call.enqueue(object : Callback<CityResponse> {
            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
                if (response.isSuccessful) {
                    val cities = response.body()?.value ?: emptyList()
                    Log.d("UserDetail", "Cities fetched: ${cities.size}")
                    setupCitySpinner(cities)
                } else {
                    Log.e("UserDetail", "Failed to fetch data: ${response.errorBody()?.string()}")
                    showToast("Failed to fetch data")
                }
            }

            override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                Log.e("UserDetail", "Error fetching data: ${t.message}")
                showToast("Error: ${t.message}")
            }
        })
    }

    private fun fetchDistricts(cityId: String) {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.getDistricts("eb21f3367d08d4ea4c6052fe2c5e4432203f67b51fee14ae7661db0d213de50f", cityId)

        call.enqueue(object : Callback<DistrictResponse> {
            override fun onResponse(call: Call<DistrictResponse>, response: Response<DistrictResponse>) {
                if (response.isSuccessful) {
                    val districts = response.body()?.value ?: emptyList()
                    Log.d("UserDetail", "Districts fetched: ${districts.size}")
                    setupDistrictSpinner(districts)
                } else {
                    Log.e("UserDetail", "Failed to fetch data: ${response.errorBody()?.string()}")
                    showToast("Failed to fetch data")
                }
            }

            override fun onFailure(call: Call<DistrictResponse>, t: Throwable) {
                Log.e("UserDetail", "Error fetching data: ${t.message}")
                showToast("Error: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupProvinceSpinner(provinces: List<Province>) {
        if (provinces.isEmpty()) {
            showToast("No provinces available")
            return
        }

        val provinceNames = provinces.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinceNames).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerProvince.adapter = adapter

        spinnerProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                Log.d("UserDetail", "Selected Province ID: ${selectedProvince.id}")
                fetchCities(selectedProvince.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupCitySpinner(cities: List<City>) {
        if (cities.isEmpty()) {
            showToast("No cities available")
            return
        }

        val cityNames = cities.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityNames).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerCity.adapter = adapter

        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCity = cities[position]
                Log.d("UserDetail", "Selected City ID: ${selectedCity.id}")
                fetchDistricts(selectedCity.id)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupDistrictSpinner(districts: List<District>) {
        if (districts.isEmpty()) {
            showToast("No districts available")
            return
        }

        val districtNames = districts.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, districtNames).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerDistrict.adapter = adapter
    }

    private fun saveUserData() {
        val email = emailET.text.toString()
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val tlp = tlpET.text.toString()
        val province = spinnerProvince.selectedItem.toString()
        val city = spinnerCity.selectedItem.toString()
        val district = spinnerDistrict.selectedItem.toString()

        // Validasi input
        val isFirstNameEmpty = firstName.isBlank()
        val isLastNameEmpty = lastName.isBlank()
        val isTlpEmpty = tlp.isBlank()
        val isGenderNotSelected = genderRadioGroup.checkedRadioButtonId == -1

        // Menetapkan visibilitas teks alert berdasarkan input kosong
        alertfirstnameTV.visibility = if (isFirstNameEmpty) View.VISIBLE else View.GONE
        alertlastnameTV.visibility = if (isLastNameEmpty) View.VISIBLE else View.GONE
        alerttlpTV.visibility = if (isTlpEmpty) View.VISIBLE else View.GONE

        // Pesan kesalahan spesifik
        val errorMessage = when {
            isFirstNameEmpty -> "Please enter your first name"
            isLastNameEmpty -> "Please enter your last name"
            isTlpEmpty -> "Please enter your phone number"
            isGenderNotSelected -> "Please select your gender"
            else -> null
        }

        // Jika ada input yang kosong, tampilkan pesan kesalahan
        if (errorMessage != null) {
            showToast(errorMessage)
            return
        }

        // Menonaktifkan EditText email
        emailET.isFocusable = false
        emailET.isFocusableInTouchMode = false

        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

        val userMap = hashMapOf(
            "email" to email,
            "firstName" to firstName,
            "lastName" to lastName,
            "tlp" to tlp,
            "province" to province,
            "city" to city,
            "district" to district,
            "gender" to selectedGender
        )

        db.collection("users")
            .add(userMap)
            .addOnSuccessListener { documentReference ->
                Log.d("UserDetail", "DocumentSnapshot added with ID: ${documentReference.id}")
                showToast("Data saved successfully")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Log.w("UserDetail", "Error adding document", e)
                showToast("Error saving data")
            }
    }



}
