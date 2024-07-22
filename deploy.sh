#!/bin/bash

REPO="PENEKhun/baekjoon-java-starter"
RELEASE_NAME="Release"
RELEASE_DESCRIPTION="This is the release description."
BUILD_DIR="build/libs"
BOJ_STARTER_GIT_TOKEN=$BOJ_STARTER_GIT_TOKEN

check_github_token() {
  if [ -z "$BOJ_STARTER_GIT_TOKEN" ]; then
    echo "Error: BOJ_STARTER_GIT_TOKEN is not set. Please set the BOJ_STARTER_GIT_TOKEN environment variable."
    exit 1
  fi
}

check_jq_installed() {
  if ! command -v jq &> /dev/null; then
    echo "jq could not be found. Please install jq to continue."
    exit 1
  fi
}

get_latest_version() {
  GITHUB_API_URL="https://api.github.com/repos/$REPO/releases/latest"
  LATEST_VERSION=$(curl -s -H "Authorization: token $BOJ_STARTER_GIT_TOKEN" $GITHUB_API_URL | jq -r ".tag_name")
  if [ "$LATEST_VERSION" == "null" ] || [ -z "$LATEST_VERSION" ]; then
    echo "Failed to fetch the latest release version."
    exit 1
  fi
  echo "Latest release version: $LATEST_VERSION"
}

get_version() {
  read -p "Enter the version (e.g., 1.0.0-alpha): " VERSION
  if [[ ! "$VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+-[a-zA-Z]+$ ]]; then
    echo "Invalid version format. Please use the format: numbers.numbers.numbers-letters (e.g., 0.0.3-alpha)"
    exit 1
  fi
}

build_project() {
  ./gradlew clean build -PdeployMode -PprojVersion=$VERSION
  if [ $? -ne 0 ]; then
    echo "Build failed."
    exit 1
  fi
}

find_jar_file() {
  FILE_PATH=$(ls -t $BUILD_DIR/*.jar | head -n 1)
  if [ ! -f "$FILE_PATH" ]; then
    echo "JAR file not found."
    exit 1
  fi
}

create_release() {
  GITHUB_API_URL="https://api.github.com/repos/$REPO/releases"
  RELEASE_RESPONSE=$(curl -s -X POST $GITHUB_API_URL \
  -H "Authorization: token $BOJ_STARTER_GIT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "tag_name": "'"$VERSION"'",
    "name": "'"$RELEASE_NAME $VERSION"'",
    "body": "'"$RELEASE_DESCRIPTION"'",
    "draft": false,
    "prerelease": false
  }')
  RELEASE_ID=$(echo "$RELEASE_RESPONSE" | jq -r '.id')
  if [ "$RELEASE_ID" == "null" ]; then
    echo "Failed to create a release."
    echo "Response: $RELEASE_RESPONSE"
    exit 1
  fi
  UPLOAD_URL=$(echo "$RELEASE_RESPONSE" | jq -r '.upload_url' | sed -e "s/{?name,label}//")
}

upload_file() {
  FILE_NAME=$(basename "$FILE_PATH")
  UPLOAD_RESPONSE=$(curl -s -X POST "$UPLOAD_URL?name=$FILE_NAME" \
  -H "Authorization: token $BOJ_STARTER_GIT_TOKEN" \
  -H "Content-Type: application/octet-stream" \
  --data-binary @"$FILE_PATH")
  if echo "$UPLOAD_RESPONSE" | jq -e '.id' > /dev/null; then
    echo "File uploaded successfully."
  else
    echo "Failed to upload the file."
    echo "Response: $UPLOAD_RESPONSE"
    exit 1
  fi
  echo "Release created and file uploaded successfully."
}

main() {
  check_github_token
  check_jq_installed
  get_latest_version
  get_version
  build_project
  find_jar_file
  create_release
  upload_file
}

main
