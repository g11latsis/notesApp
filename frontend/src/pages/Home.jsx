import React, { useEffect, useState } from "react";
import { Container, Row, Col, Button } from "react-bootstrap";
import NoteCard from "../components/NoteCard";
import NoteModal from "../components/NoteModal";
import Header from "../components/Header";
import axios from "axios";
import DeleteModal from "../components/DeleteModal";

const Home = ({ user, handleLogout }) => {
  const [notes, setNotes] = useState([]);
  const [showNoteModal, setShowNoteModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [currentNote, setCurrentNote] = useState(null);
  const [noteToDelete, setNoteToDelete] = useState(null);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    const loadNotes = async () => {
      try {
        const userId = user.id;
        const response = await axios.get(
          `http://localhost:8080/api/notes/${userId}`
        );
        setNotes(response.data);
      } catch (error) {
        console.error("Error loading notes:", error);
      }
    };

    loadNotes();
  }, [user]);

  const handleSave = async (note) => {
    try {
      const userId = user.id;
      if (!note.title || !note.description) {
        setError("Title and description cannot be empty");
        return;
      }
      note.date = new Date().toISOString();
      if (note.id) {
        await axios.put(`http://localhost:8080/api/notes/${note.id}`, note);
      } else {
        await axios.post(`http://localhost:8080/api/notes/${userId}`, note);
      }
      const response = await axios.get(
        `http://localhost:8080/api/notes/${userId}`
      );
      setNotes(response.data);
      setSuccess("Note saved successfully");
      setShowNoteModal(false); // Close modal after save
    } catch (error) {
      console.error("Error saving note:", error);
      setError("Error saving note");
    }
  };

  const handleEdit = (note) => {
    setCurrentNote(note);
    setShowNoteModal(true);
  };

  const handleAdd = () => {
    setCurrentNote(null);
    setShowNoteModal(true);
  };

  const handleDelete = async (noteId) => {
    try {
      await axios.delete(`http://localhost:8080/api/notes/${noteId}`);
      setNotes(notes.filter((note) => note.id !== noteId));
      setShowDeleteModal(false);
    } catch (error) {
      setError("Error deleting note");
    }
  };

  const toggleDeleteModal = (note) => {
    setNoteToDelete(note);
    setShowDeleteModal(true);
  };


  return (
    <div>
      <Header
        isLoggedIn={true}
        user={user.username}
        handleLogout={handleLogout}
      />
      <Container className="mt-3">
        <Button variant="success" onClick={handleAdd} className="mb-3">
          Add Note
        </Button>
        <Row>
          {notes.map((note) => (
            <Col key={note.id} md={4}>
              <NoteCard
                note={note}
                key={note.id}
                onEdit={() => handleEdit(note)}
                onDelete={() => toggleDeleteModal(note)}
              />
            </Col>
          ))}
        </Row>
      </Container>
      <NoteModal
        show={showNoteModal}
        handleClose={() => setShowNoteModal(false)}
        handleSave={handleSave}
        note={currentNote}
      />
      <DeleteModal
        show={showDeleteModal}
        onHide={() => setShowDeleteModal(false)}
        onConfirm={() => handleDelete(noteToDelete.id)}
      />
    </div>
  );
};

export default Home;
