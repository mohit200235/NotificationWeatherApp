
# NotificationWeatherApp

This is a simple Android weather app that provides real-time weather information for a specified location. The app uses the OpenWeatherMap API to fetch weather data and displays it to the user. Additionally, the app allows users to set up weather notifications for their preferred location and conditions.


# Features
-**Real-time Weather Data**: The app fetches and displays real-time weather information for the specified location, including temperature, condition, and location name.

-**Weather Notifications**: Users can set up weather notifications based on specific conditions and temperature thresholds. When triggered, the app sends a notification to the user with weather details.
-**Location Selection**: Users can choose their desired location by searching for it or allowing the app to use their current GPS location.

-**Hourly Forecast**: The app provides an hourly forecast for the current day, allowing users to see the weather conditions for different times.

# Installation
To use this app, follow these steps:

1. Clone or download the repository to your local machine.

2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or physical device.

4. Grant location permissions when prompted (for current weather information).

 # Usage

Upon launching the app, you'll be presented with the following options:

-**View Current Weather**: The app will initially display the current weather for your default location (Haryana in this case). You can change the location by tapping the floating action button (FAB) and selecting a new location.

-**Set Weather Notification**: You can set up weather notifications by tapping the "Add Notification" button. This allows you to specify the temperature and conditions that trigger a notification.

-**Hourly Forecast**: The app provides an hourly forecast for the current day, displayed as a horizontal scrollable list.

-**Pull to Refresh**: You can manually refresh the weather data by pulling down on the screen.

-**Toggle Location Updates**: The LocationViewModel class in the ViewModel package provides functionality to toggle location updates.

# Dependencies
 - Retrofit for making API requests to OpenWeatherMap.

 - Glide for loading and displaying images (weather icons).

 - AndroidX libraries for UI components and ViewModel.

# Configuration

To use this app with your own OpenWeatherMap API key, replace the KEY variable in the MainActivity class with your API key:
The app uses several libraries and dependencies, including:

private final static String KEY = "YOUR_API_KEY_HERE";

 # Contributing
Contributions to this project are welcome! You can contribute by submitting bug reports, feature requests, or code contributions through pull requests.

 # Acknowledgments
- This app was created as a learning project to demonstrate the integration of weather data and notifications in an Android application.

 - Weather icons are provided by OpenWeatherMap and are subject to their terms of use.

 - Thanks to the developers and contributors of Retrofit, Glide, and other open-source libraries used in this project.

If you have any questions or need further assistance, please feel free to contact the project maintainer. Enjoy using the Notification Weather App!

# Author

**Mohit Gupta**
