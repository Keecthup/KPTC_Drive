package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StorageFile {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
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
