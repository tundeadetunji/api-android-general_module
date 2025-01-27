# Code Repertoire
A programmer's toolkit - contains handy functions for daily use.

<br />


To use, add this in your root build.gradle at the end of repositories:

```html
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the dependency:

```html
dependencies {
	implementation 'com.github.tundeadetunji:api-android-general_module:TAG'
}
```
Where TAG is the latest VCS release.

<br />
For static imports (as required):
<br />
<br />
<pre>
import static io.github.tundeadetunji.android.General.*;
import static io.github.tundeadetunji.android.Device.*;
</pre>

To check if the device is connected to internet, for example:
```java
boolean isConnectedToInternet = thereIsInternet(getApplicationContext());
```

<h3>For non static imports:</h3>
<pre>
import io.github.tundeadetunji.android.Feedback;
</pre>

To issue a toast, for example:
```java
private Feedback feedback = new Feedback(getApplicationContext());
feedback.toast("toast message");
```

<br />
<br />
For the Java version of the project, see <a href="https://github.com/tundeadetunji/api-java-code" target="_blank">here</a>.
