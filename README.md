# Foundry

The missing typekit for Android.

## Overview

Tired of manually setting the typefaces of all your views and cluttering your logic with presentation code? Foundry has your back. 

Foundry lets you style the typefaces of your views in your XML layout files and styles.

Foundry currently supports `ttf` fonts only, although you may use a different extension if required.

### Dependency

**Maven**

	<dependency>
	    <groupId>uk.co.josephearl.foundry</groupId>
	    <artifactId>foundry</artifactId>
	    <version>1.0.3</version>
	    <type>aar</type>
	</dependency>

**Gradle**

    compile 'uk.co.josephearl.foundry:foundry:1.0.3@aar'

### Using Foundry

**1. Add Foundry as a Dependency**

Add the Foundry dependency to your pom.xml or build.gradle.
	
**2. Add Typefaces to your Project**

Place your custom `ttf` fonts in your assets directory.

**3. Use Foundry as your LayoutInflater**

You can either manually create a `FoundryLayoutInflater` as required, or you can use Foundry as your default inflater
 for views by adding the following code to your `Activity` classes:

	private LayoutInflater foundryLayoutInflater;

	@Override
	public Object getSystemService(final String name) {
	    if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
	    	return getFoundryLayoutInflater();
	    }
	    return super.getSystemService(name);
	}

    @Override
	public LayoutInflater getLayoutInflater() {
	    return getFoundryLayoutInflater();
	}
	
	private LayoutInflater getFoundryLayoutInflater() {
	    if (foundryLayoutInflater == null) {
	        foundryLayoutInflater = new FoundryLayoutInflater(this, new FoundryFoundry(getAssets()));
	    }
	    return foundryLayoutInflater;
	}
	
	
**4. Apply Foundry to your Layouts**

Add the following to the root view in your layout file:

	xmlns:app="http://schemas.android.com/apk/res-auto"
	
Add `app:foundryTypeface` attributes to your views:

	<TextView
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="Sample Text"
	    app:foundryTypeface="font_name"
	    />
	    
The `font_name` should match the name of your font file without the `.ttf` extension.

**Note:** you can leverage Foundry in your styles by using the `typeface` attribute without any prefix, e.g:

	<resources>
	    <style name="StyledByFoundry">
	        <item name="foundryTypeface">font_name</item>
	    </style>
	</resources>
	
Foundry cannot be used inside text styles that are applied using `android:textAppearance`.

### Building Foundry

**Requirements:**

* Maven 3.1.1 or later
* JDK 6 or 7
* Android SDK with API level 19
* `ANDROID_HOME` correctly set

#### Compile

Builds Foundry, the sample app and runs unit tests.

	$ mvn clean verify
	
#### Install

Installs the Foundry library into your local Maven repository for use as a dependency by other projects.

	$ mvn clean install -Dandroid.test.skip=true

**Note:** if you wish to generate a Gradle-compatible `aar` library rather than the Maven `apklib` simply change the packaging type used by the library.
	
#### Run Sample

Builds Foundry and deploys the sample app to any connected devices.

	$ mvn clean install -DskipTests=true && mvn android:deploy -pl sample
	
## License

	Copyright 2014 Joseph Earl.

	Licensed under the Apache License, Version 2.0 (the "License"); you may not use 
	this file except in compliance with the License.
	You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software distributed
	under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
	CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and limitations
	under the License.
