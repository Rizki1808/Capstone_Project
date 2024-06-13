package com.example.capstoneproject.ui.feature.item.minumobat

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.R
import com.example.capstoneproject.data.ApplicationViewModelFactory
import com.example.capstoneproject.data.database.Drugs
import com.example.capstoneproject.data.helper.DateHelper
import com.example.capstoneproject.databinding.ActivityTambahObatBinding

class TambahObatActivity : AppCompatActivity() {

    private var _activityDrugsTambahObatBinding: ActivityTambahObatBinding? = null
    private val binding get() = _activityDrugsTambahObatBinding!!
    private lateinit var viewModel: TambahObatViewModel

    private var isEdit = false
    private var drugs: Drugs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDrugsTambahObatBinding = ActivityTambahObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@TambahObatActivity)

        drugs = intent.getParcelableExtra(EXTRA_DRUGS)
        if (drugs != null) {
            isEdit = true
        } else {
            drugs = Drugs(0, "", "", "", "", "")
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (drugs != null) {
                drugs?.let { drugs ->
                    binding.edNamaObat.setText(drugs.name)
                    binding.edDosis.setText(drugs.dose)
                    binding.edJamMinum.setText(drugs.timeToTake)
                    binding.edAturan.setText(drugs.instructions)
                    binding.edCatatan.setText(drugs.notes)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSimpan.text = btnTitle

        binding.btnSimpan.setOnClickListener {
            val name = binding.edNamaObat.text.toString().trim()
            val dose = binding.edDosis.text.toString().trim()
            val timeToTake = binding.edJamMinum.text.toString().trim()
            val instructions = binding.edAturan.text.toString().trim()
            val notes = binding.edCatatan.text.toString().trim()

            when {
                name.isEmpty() -> {
                    binding.edNamaObat.error = getString(R.string.empty)
                }
                dose.isEmpty() -> {
                    binding.edDosis.error = getString(R.string.empty)
                }
                timeToTake.isEmpty() -> {
                    binding.edJamMinum.error = getString(R.string.empty)
                }
                instructions.isEmpty() -> {
                    binding.edAturan.error = getString(R.string.empty)
                }
                else -> {
                    drugs.let { drugs ->
                        drugs?.name = name
                        drugs?.dose = dose
                        drugs?.timeToTake = timeToTake
                        drugs?.instructions = instructions
                        drugs?.notes = notes
                    }
                    if (isEdit) {
                        viewModel.updateDrug(drugs as Drugs)
                        showToast(getString(R.string.changed))
                    } else {
                        drugs.let { drugs ->
                            drugs?.date = DateHelper.getCurrentDate()
                        }
                        viewModel.insertDrug(drugs as Drugs)
                        showToast(getString(R.string.added))
                    }
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDrugsTambahObatBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): TambahObatViewModel{
        val factory = ApplicationViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TambahObatViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.drugs_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    viewModel.deleteDrug(drugs as Drugs)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    companion object {
        const val EXTRA_DRUGS = "extra_drugs"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}