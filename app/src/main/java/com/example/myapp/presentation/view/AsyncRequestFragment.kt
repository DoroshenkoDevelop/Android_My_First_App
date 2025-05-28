import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapp.R
import com.example.myapp.data.DataRepository
import com.example.myapp.domain.FetchDataUseCase
import com.example.myapp.presentation.state.DataState
import com.example.myapp.presentation.viewmodel.AsyncRequestViewModel
import kotlinx.coroutines.flow.collect

class AsyncRequestFragment : Fragment(R.layout.fragment_async_request) {

    private lateinit var viewModel: AsyncRequestViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        textView = view.findViewById(R.id.resultTextView)
        button = view.findViewById(R.id.loadButton)

        // Инициализация ViewModel
        val repository = DataRepository()
        val useCase = FetchDataUseCase(repository)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return AsyncRequestViewModel(useCase) as T
            }
        }).get(AsyncRequestViewModel::class.java)

        // Подписка на StateFlow
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                updateUI(state)
            }
        }

        button.setOnClickListener {
            viewModel.loadData()
        }
    }

    private fun updateUI(state: DataState) {
        when (state) {
            DataState.Idle -> {
                progressBar.visibility = View.GONE
                textView.text = "Нажмите кнопку для загрузки"
            }
            DataState.Loading -> {
                progressBar.visibility = View.VISIBLE
                textView.text = "Загрузка..."
            }
            is DataState.Success -> {
                progressBar.visibility = View.GONE
                textView.text = state.data
            }
            is DataState.Error -> {
                progressBar.visibility = View.GONE
                textView.text = "Ошибка: ${state.message}"
            }
        }
    }
}