package net.andymc12.restructure.json.sample;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class determines the application path for the app. It's presence means that we don't need to package a
 * web.xml file with the application.
 */
@ApplicationPath("/services/v2")
public class SampleRestApplication extends Application {
}
