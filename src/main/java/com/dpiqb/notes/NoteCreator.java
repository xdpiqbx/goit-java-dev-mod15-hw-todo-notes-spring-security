package com.dpiqb.notes;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "note_creator")
public class NoteCreator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String password;
  private String authorities;
}