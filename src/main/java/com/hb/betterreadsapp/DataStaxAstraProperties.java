package com.hb.betterreadsapp;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * to connect to datastax astra db instance
 */
@ConfigurationProperties(value = "datastax.astra")
public class DataStaxAstraProperties {

	private File secureConnectBundle;

	public File getSecureConnectBundle() {
		return secureConnectBundle;
	}

	public void setSecureConnectBundle(File secureConnectBundle) {
		this.secureConnectBundle = secureConnectBundle;
	}

}
