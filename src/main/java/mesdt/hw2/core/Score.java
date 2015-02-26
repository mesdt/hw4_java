package mesdt.hw2.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scores")
public class Score {

	@EmbeddedId
	private Id id;

	@Column(nullable = false)
	private Integer score;

	public Subject getSubject() {
		return id.subject;
	}

	public Integer getScore() {
		return score;
	}

	@Override
	public String toString() {
		return String.format("(score %s %d)", id, score);
	}

	@Embeddable
	// TODO: override equals(), hashCode()
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne(optional = false)
		// N.B.
		@JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE"))
		private Student student;

		@ManyToOne(optional = false)
		private Subject subject;

		@Override
		public String toString() {
			return String.format("%s %s", student, subject);
		}

	}

}
