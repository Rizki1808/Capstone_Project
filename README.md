# SelfMedic: Pioneering Remote Healthcare through an Innovative Application

## Overview
SelfMedic is a healthcare and medical application designed to empower communities by providing innovative health solutions based on AI and mobile technology. The app aids in identifying health issues, especially skin diseases, using readily available tools at home.

## Features
- AI-based disease prediction
- Image classification of skin diseases and scars
- Health daily news
- Medication reminders
- Search for the closest hospital

## Project Structure
The project is divided into three main parts: Machine Learning, Mobile Development, and Cloud Computing.

### Machine Learning
- **Models**: Built using TensorFlow and TensorFlow.js.
  - **Skin Disease Image Classification**: Uses transfer learning with MobileNet.
  - **Facial Skin Disease Image Classification**: Uses CNN method.
- **Data Pipeline**: Preprocessing data using Missing Values Imputation.

### Mobile Development
- **UI/UX Prototyping**: Designed using Figma.
- **Development**: Implemented in Android Studio using Kotlin.
- **Libraries**:
  - Glide for image loading
  - CameraX for camera features
  - Retrofit for HTTP client
  - Room for local data storage
  - Coroutines for Kotlin code management

### Cloud Computing
- **Communication**: Implemented using MQTT.
- **Backend**:
  - Database and API created and deployed on Google Cloud.
  - Dashboard for monitoring service availability, logging, and housekeeping.

## Installation and Setup

### Prerequisites
- [Android Studio](https://developer.android.com/studio)
- [Node.js](https://nodejs.org/) (for TensorFlow.js)
- [Python](https://www.python.org/) (for TensorFlow)
- [Google Cloud SDK](https://cloud.google.com/sdk)

### Step-by-Step Guide

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Rizki1808/Capstone_Project.git
   cd Capstone_Project
