#!/bin/bash

# Start Appium in background
echo "🚀 Starting Appium..."
appium > appium.log 2>&1 &
APPIUM_PID=$!
sleep 5

# Run the tests
echo "🧪 Running tests..."
./gradlew test

# Kill Appium after tests
echo "🛑 Stopping Appium..."
kill $APPIUM_PID

echo "✅ Done!"
