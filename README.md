# ChatApp With Google Gemini

A support help chat application built using the [Google Gemini API](https://developers.google.com/ai/generativeai) for natural language processing. This app uses **Kotlin**, **Jetpack Compose**, **Coroutines**, and **Flow** to create a real-time chat interface that provides automated responses for user queries.

## Features

- **Google Gemini API Integration**: Leveraging the power of Google Gemini for generating automated responses to support queries.
- **Real-time Chat**: Built with Jetpack Compose for a modern, declarative UI that updates in real-time.
- **Kotlin Coroutines & Flow**: For asynchronous programming and data stream management, ensuring smooth and efficient data handling.
- **Clean Architecture**: Follows clean architecture principles for better separation of concerns, testability, and maintainability.

## Screenshots

![image](https://github.com/user-attachments/assets/de66cd4e-fe62-45c3-9309-74a311bfa04e)


## Technology Stack

- **Kotlin**: Language used for Android development.
- **Jetpack Compose**: Modern UI toolkit for building declarative UIs.
- **Coroutines**: For managing background tasks and asynchronous programming.
- **Flow**: For handling data streams asynchronously in a reactive way.
- **Google Gemini API**: For generating natural language responses.

## Installation

To get a local copy up and running, follow these steps:

### Prerequisites

- **Android Studio**: Download and install the latest version of [Android Studio](https://developer.android.com/studio).
- **Google Gemini API Key**: You need to sign up for Google Gemini API and obtain an API key. Refer to the [Gemini API Documentation](https://developers.google.com/ai/generativeai) for details.

### Clone the Repository

```bash
git clone https://github.com/sudhanshshah4/ChatAppWithGoogleGemini.git
```
**Set Up API Key**
Obtain your Google Gemini API key.
Create a apikey.properties file in your root project directory.
Add the following line to local.properties:

geminiApiKey=YOUR_API_KEY_HERE

Build and Run the Project
Open the project in Android Studio.
Sync Gradle by clicking Sync Now when prompted.
Build and run the app on an emulator or physical device by clicking the Run button.

**Usage**
- **Start the app**: Once the app is running, you can enter your query in the chat interface.
- **Get Responses**: The app uses the Google Gemini API to generate responses to your queries in real-time.
- **Multi-step Guidance**: It handles different states , providing helpful automated replies.

**Project Structure**
The project follows Clean Architecture principles, with separation into different layers:

- **UI Layer**: Contains Jetpack Compose code for the user interface.
- **Domain Layer**: Contains business logic and interacts with the Google Gemini API.
- **Data Layer**: Manages data sources and repositories for retrieving data.

**Contributing**
Contributions are welcome! Please follow these steps:

-Fork the repository.
- Create a feature branch (git checkout -b feature-branch).
- Commit your changes (git commit -m 'Add some feature').
- Push to the branch (git push origin feature-branch).
- Open a pull request.

**License**
This project is licensed under the MIT License - see the LICENSE file for details.

**Contact**
If you have any questions, feel free to reach out:
- **Email**:sudhansh.shah2@gmail.com
- **GitHub**: https://github.com/sudhanshshah4



