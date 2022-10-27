
# Android Brivo Networking library.

Android Networking library.

- [Features](#features)
- [Requirements](#requirements)
- [Technologies](#technologies)
- [Usage](#usage)

# Features

[x] Creating HTTP Services.

[x] Custom authentication.

# Requirements

[x] Android Studio Dolphin | 2021.3.1

[x] Kotlin Language Version 1.7.10

# Technologies

[x] Coroutines

[x] Retrofit

[x] OkHttp

[x] Chucker

[x] Dagger HILT

[x] KotlinX Serialization

[x] DataStore

## Usage

In order to use the package in a new project one nees to use:
XCode -> File -> Add Packages -> Add the package URL in the "Search or Enter package URL" and use the version needed.
The URL is: git@bitbucket.org:brivoinc/mobile-networking-ios.git

### OnAirHTTP Service
By default the library provides an OnAir HTTP Service.
To create the service first we need to create a configuration and the default service:
```kotlin
	  lateinit var activityDelegate: IBrivoNetworkingActivityDelegate

fun configure(networkingActivityDelegate: IBrivoNetworkingActivityDelegate = DefaultBrivoNetworkingActivityDelegateImpl()) {
    activityDelegate = networkingActivityDelegate
}
```
To use the service we need to call the public functions like this:
```swift
	onAirService
    	.getSites()
        .sink { completion in
        	// when the stream either completes or it has an error
        } receiveValue: { [weak self] sites in
           // here we get the sites
        }
        .store(in: &cancellables)
```

### Creating Other HTTP Services
The library allows to create custom HTTP services with or without authentication handling.
For creating a new HTTP service we need to define the configurations and then create the services:
```swift
	import BrivoNetworking
	...
    let authenticator = CustomAuthenticator()
    let oauthConfig = OAuthConfiguration(oauthUrl: <auth url here>,
                                		 clientId: <client id>,
                         				 clientSecret: <client secret>)
    let brivoHTTPService = BrivoNetworking.createHTTPService(apiBaseUrl: <api base url>,
                                                      	     oauthConfiguration: oauthConfig,
                                                             authenticator: authenticator,
                                                             tokens: tokens)
```
Note: you can define your own authenticator that will refresh the token when the access token is getting a authentication error.
Your authentication needs to inherit the BrivoAuthenticator class and override the variables and members so that it can detect an authentication failure and recover from it.
After we performed this setup we can create HTTP calls like this:
```swift
   brivoHTTPService
   .performHttpRequest(endpoint: OnAirEndpoint.sites)
    	.sink { completion in
        	// when the stream either completes or it has an error
        } receiveValue: { [weak self] sites in
           // here we get the sites
        }
        .store(in: &cancellables)
```

### Cancelling HTTP Calls
The library will automatically cancell HTTP calls when they are added in the Combine AnyCancellable and they are released from memory.
We can also cancel an on going HTTP call when we call "cancellable.cancel()" on one cancellable.

### Errors
The library will send errors based on what problems appear during a HTTP call.
There are multiple types of errors:
```swift
	public enum BrivoNetworkingError: LocalizedError {
    	case configurationError(reason: String)
    	case invalidURL(urlString: String)
    	case httpValidationError(code: Int, reason: String, response: String? = nil)
    	case otherError(reason: String, response: String? = nil)
    	case parsingError(error: Error, response: String? = nil)
    	case authorizationError
    	case emptyResponse
	}
```
When the errors can happen due to multiple reasons there will also be a "reason" string along with the error.
In case on not being able to refresh the token the library will send a "authorizationError" error.

### Activity delegate
The library provides an activity delegate object that can intercept what is happening inside the library.
To use this we need to set this delegate when we configure the library:
```swift
	let activityDelegate = CustomActivityDelegate()
	BrivoNetworking.setActivityDelegate()
```
The "activityDelegate" object need to implement the "BrivoNetworkingActivityDelegate" interface and it will get messsages when logs or errors happen.

## Authors

Brivo Phantoms Team.
