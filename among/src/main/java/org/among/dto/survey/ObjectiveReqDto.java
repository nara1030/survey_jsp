package org.among.dto.survey;

import java.util.List;

public class ObjectiveReqDto {
	private String authorId;
	
	private List<ObjectiveTemplateReq> questions;
	
	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public List<ObjectiveTemplateReq> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ObjectiveTemplateReq> questions) {
		this.questions = questions;
	}

	public static class ObjectiveTemplateReq {
		private String question;
		
		private List<String> options;
		
		public ObjectiveTemplateReq() {
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public List<String> getOptions() {
			return options;
		}

		public void setOptions(List<String> options) {
			this.options = options;
		}
		
	}
	
}
