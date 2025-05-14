const questionArea = document.getElementById('questionArea');
const questionCountSelect = document.getElementById('questionCount');
const typeRadios = document.getElementsByName('type');
const questionCountArea = document.getElementById('questionCountArea');
const submitBtn = document.getElementById('submitBtn');

// 문항 렌더링 함수
function renderQuestions() {
  const count = parseInt(questionCountSelect.value);
  const selectedType = [...typeRadios].find(r => r.checked).value;

  questionArea.innerHTML = ''; // 초기화

  if (!count || isNaN(count)) return;

  for (let i = 0; i < count; i++) {
    const div = document.createElement('div');
    div.className = 'mb-3';

    let innerHtml = `
      <label class="form-label">질문 ${i + 1}</label>
      <input type="text" name="questions[${i}].question" class="form-control mb-2" placeholder="질문 내용을 입력하세요" required>
    `;

    if (selectedType === 'OBJECTIVE') {
      // 객관식일 때 옵션 입력 필드 추가
      for (let j = 0; j < 3; j++) {
        innerHtml += `
          <input type="text" name="questions[${i}].options[${j}]" class="form-control mb-1" placeholder="보기 ${j}" required>
        `;
      }
    } else if (selectedType === 'SUBJECTIVE') {
      // 주관식일 때 답변 입력 필드 추가
      innerHtml += `
        <input type="text" name="questions[${i}].answer" class="form-control mb-2" placeholder="답변을 입력하세요" required>
      `;
    }

    div.innerHTML = innerHtml;
    questionArea.appendChild(div);
  }

  // 제출 버튼 보이기
  submitBtn.style.display = 'block';
}

// 설문 유형 변경 시
typeRadios.forEach(radio => radio.addEventListener('change', () => {
  // 설문 유형 선택 후 문항 수 영역 보이게 하기
  questionCountArea.style.display = 'block';
  submitBtn.style.display = 'none'; // 설문 유형 선택 시 제출 버튼 숨기기
  questionArea.innerHTML = ''; // 문항 영역 초기화
}));

// 문항 수 선택 시
questionCountSelect.addEventListener('change', renderQuestions);

// 페이지(DOM) 로드 시 초기 상태로 문항 수 영역 숨기기
window.addEventListener('DOMContentLoaded', () => {
  questionCountArea.style.display = 'none'; // 문항 수 선택 영역 숨기기
  submitBtn.style.display = 'none'; // 제출 버튼 숨기기
});