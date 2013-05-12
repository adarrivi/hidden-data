package com.hidden.data.producer.book.text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hidden.data.producer.exception.ProducerException;

public class TextFile {

	private File file;
	private BufferedReader reader;
	private boolean fileOpen = false;

	public TextFile(File file) {
		this.file = file;
	}

	public void openFile() {
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			reader = new BufferedReader(new InputStreamReader(in));
			fileOpen = true;
		} catch (FileNotFoundException e) {
			throw new ProducerException(e);
		}
	}

	public String readLine() {
		try {
			assertFileOpen();
			return reader.readLine();
		} catch (IOException e) {
			throw new ProducerException(e);
		}
	}

	private void assertFileOpen() {
		if (!fileOpen) {
			throw new ProducerException("You must open first the file");
		}
	}

	public void closeFile() {
		try {
			reader.close();
			fileOpen = false;
		} catch (IOException e) {
			throw new ProducerException(e);
		}
	}

}
