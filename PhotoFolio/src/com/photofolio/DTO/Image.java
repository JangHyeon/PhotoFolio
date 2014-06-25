package com.photofolio.DTO;

public class Image {
private int image_index;
private int index;
private int ref;
private String ori_name;
private String file_name;
private int    file_size;
public int getImage_index() {
	return image_index;
}
public void setImage_index(int image_index) {
	this.image_index = image_index;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public int getRef() {
	return ref;
}
public void setRef(int ref) {
	this.ref = ref;
}
public String getOri_name() {
	return ori_name;
}
public void setOri_name(String ori_name) {
	this.ori_name = ori_name;
}
public String getFile_name() {
	return file_name;
}
public void setFile_name(String file_name) {
	this.file_name = file_name;
}
public int getFile_size() {
	return file_size;
}
public void setFile_size(int file_size) {
	this.file_size = file_size;
}

}
