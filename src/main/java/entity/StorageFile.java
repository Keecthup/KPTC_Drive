package entity;


public class StorageFile {

  private long id;
  private String fileTitle;
  private double size;
  private long fileUserId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFileTitle() {
    return fileTitle;
  }

  public void setFileTitle(String fileTitle) {
    this.fileTitle = fileTitle;
  }


  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }


  public long getFileUserId() {
    return fileUserId;
  }

  public void setFileUserId(long fileUserId) {
    this.fileUserId = fileUserId;
  }

}
