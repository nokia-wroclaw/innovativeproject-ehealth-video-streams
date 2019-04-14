import React from 'react';

const Content = () => {
    return (
        <div className="tab-content content-color" id="pills-tabContent" style={{height: '100vh'}}>
          <div className="tab-pane fade show active" id="instruction" role="tabpanel" aria-labelledby="instruction-tab">Podstrona 1</div>
          <div className="tab-pane fade" id="pulse" role="tabpanel" aria-labelledby="pulse-tab">Podstrona 2</div>
          <div className="tab-pane fade" id="emotions" role="tabpanel" aria-labelledby="emotions-tab">Podstrona 3</div>
        </div>
    )
}

export default Content;