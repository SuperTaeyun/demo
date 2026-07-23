import {useState, useEffect} from "react";

interface Memo {
    id: number;
    title: string;
    content: string;
}

const API_URL = "http://3.34.199.160:8080/api/memos";

function App() {
    const [memos, setMemos] = useState<Memo[]>([]);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    // GET memos
    const fetchMemos = async () => {
        try {
            const response = await fetch(API_URL);
            const data = await response.json();
            setMemos(data);
        } catch (error) {
            console.error('백엔드 연결 실패:', error);
        }
    }

    const createMemo = async (e: React.SubmitEvent) => {
        e.preventDefault();
        try {
            await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({title, content}),
            });
            setTitle('');
            setContent('');
            // 데이터 생성 후 다시 불러오기
            fetchMemos();
        } catch (error) {
            console.error('메모 생성 실패:', error);
        }
    };

    const deleteMemo = async (id: number) => {
        try {
            await fetch(API_URL + `/${id}`, {
                method: 'DELETE',
            });
            fetchMemos();
        } catch (error) {
            console.error('메모 삭제 실패:', error);
        }
    };

    // 컴포넌트 로딩 과정에서 한 번 실행
    useEffect(() => {
        fetchMemos();
    }, []);

    return (
        <div style={{padding: '20px', maxWidth: '600px', margin: '0 auto', fontFamily: 'sans-serif'}}>
            <h1>Memo App</h1>

            <form onSubmit={createMemo} style={{display: 'flex', gap: '10px', marginBottom: '20px'}}>
                <input
                    type="text"
                    placeholder="제목"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                    style={{flex: 1, padding: '8px'}}
                />
                <input
                    type="text"
                    placeholder="내용"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    required
                    style={{flex: 2, padding: '8px'}}
                />
                <button type="submit" style={{padding: '8px 16px', cursor: 'pointer'}}>등록</button>
            </form>

            <ul style={{listStyle: 'none', padding: 0}}>
                {memos.map((memo) => (
                    <li key={memo.id}
                        style={{border: '1px solid #ddd', padding: '15px', marginBottom: '10px', borderRadius: '5px'}}>
                        <h3 style={{margin: '0 0 10px 0'}}>{memo.title}</h3>
                        <p style={{margin: '0 0 15px 0', color: '#555'}}>{memo.content}</p>
                        <button onClick={() => deleteMemo(memo.id)} style={{
                            color: 'white',
                            backgroundColor: '#ff4444',
                            border: 'none',
                            padding: '5px 10px',
                            borderRadius: '3px',
                            cursor: 'pointer'
                        }}>
                            삭제
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default App;