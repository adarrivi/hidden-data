package com.hidden.data.producer.file;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hidden.data.producer.exception.ProducerException;

public class BufferFileReader implements FileBufferReader {

	private File file;
	private BufferedReader reader;

	public BufferFileReader(File file) {
		this.file = file;
		openFile();
	}

	private void openFile() {
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			reader = new BufferedReader(new InputStreamReader(in));
		} catch (FileNotFoundException e) {
			throw new ProducerException(e);
		}
	}

	@Override
	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new ProducerException(e);
		}
	}

	@Override
	public void close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new ProducerException(e);
		}
	}

}
