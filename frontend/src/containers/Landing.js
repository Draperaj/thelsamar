import './Landing.css';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom'

export default function landing() {
    return (<div className="landing">
        <div className="p-5 mb-4 rounded-3">
        <div className="container-fluid py-5">
            <h1 className="display-5 fw-bold">Thelsamar</h1>
            <p className="fs-4">Order and tax calculation app.</p>
            <Link to="/order">
                <Button block size="lg">
                    Get Started
                </Button>
            </Link>
        </div>
        </div>
    </div>);
}