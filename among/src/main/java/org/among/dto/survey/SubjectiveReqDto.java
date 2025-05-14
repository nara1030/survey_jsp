package org.among.dto.survey;

import java.util.List;

public class SubjectiveReqDto {
	private String authorId; // JSP에서 세션에서 꺼내 세팅 후 넘겨주기
	
	private List<SubjectiveTemplateReq> questions;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public List<SubjectiveTemplateReq> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SubjectiveTemplateReq> questions) {
		this.questions = questions;
	}

	public static class SubjectiveTemplateReq {
		private String question;
		
		private String answer;
		
		public SubjectiveTemplateReq() {
        }

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		@Override
		public String toString() {
			return "SubjectiveTemplateReq [question=" + question + ", answer=" + answer + "]";
		}
		
	}

	@Override
	public String toString() {
		return "SubjectiveReqDto [authorId=" + authorId + ", questions=" + questions + "]";
	}
	
}
