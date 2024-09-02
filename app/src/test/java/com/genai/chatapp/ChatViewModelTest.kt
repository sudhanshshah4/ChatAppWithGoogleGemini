import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.genai.chatapp.ChatViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.asTextOrNull
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    // This rule allows LiveData to execute synchronously during testing
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Create a test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: ChatViewModel
    private lateinit var mockModel: GenerativeModel

    @Before
    fun setup() {
        // Set up a dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Mock the GenerativeModel
        mockModel = mockk()

        // Set up the ViewModel with a mock GenerativeModel
        viewModel = ChatViewModel()

    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after testing
        Dispatchers.resetMain()
    }

    @Test
    fun `sendMessage adds user message to messages flow`() = runTest {
        // Given
        val userInput = "Hello Bot"

        // When
        viewModel.sendMessage(userInput)
        advanceUntilIdle()
        // Then
        val messages = viewModel.messages.first()
        assertEquals(1, messages.size)
        assertEquals(userInput, messages[0].content)
        assertTrue(messages[0].isUser)
    }

    @Test
    fun `sendMessage adds bot response to messages flow`() = runTest {
        // Given
        val userInput = "Hello Bot"
        val botResponse = "Hello User"


        coEvery { mockModel.generateContent(*anyVararg()) } returns createMockResponse(botResponse)

        // When
        viewModel.sendMessage(userInput)

        // Advance the dispatcher to simulate coroutine execution
        advanceUntilIdle()

        // Then
        val messages = viewModel.messages.first()
        assertEquals(1, messages.size)

        // Verify the user's message
        assertEquals(userInput, messages[0].content)
        assertTrue(messages[0].isUser)

    }

    @Test
    fun `sendMessage handles null or empty bot response`() = runTest {
        // Given
        val userInput = "How are you?"

        // Mock the bot response as null
        coEvery { mockModel.generateContent(userInput) } returns createMockResponse(null)

        // When
        viewModel.sendMessage(userInput)

        // Advance the dispatcher to simulate coroutine execution
        advanceUntilIdle()

        // Then
        val messages = viewModel.messages.first()
        assertEquals(1, messages.size)

        // Verify the user's message
        assertEquals(userInput, messages[0].content)
        assertTrue(messages[0].isUser)

    }


    // Helper function to create a mock response for GenerativeModel
    private fun createMockResponse(botResponse: String?): GenerateContentResponse {
        return mockk {
            every {
                candidates.firstOrNull()?.content?.parts?.firstOrNull()?.asTextOrNull()
            } returns botResponse
        }
    }
}
