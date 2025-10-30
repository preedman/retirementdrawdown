import { _ as _r, $ as $$1, J as Jt, j as je, r as rt, Y as Ya } from "./indexhtml-B7WNcaKJ.js";
import { g } from "./state-eevs1asN-CG66D4x8.js";
import { y, p } from "./overlay-monkeypatch-BpxWVdY5-9PtU06cT.js";
import { o as o$1 } from "./base-panel-Cl8rL5aQ-D64Vh1W3.js";
import { r as r$1 } from "./icons-DrBP115r-ULtKT6RW.js";
const x = "copilot-feedback-panel{display:flex;flex-direction:column;font:var(--font-xsmall);--vaadin-input-field-label-font-size: var(--font-size-1);padding:var(--space-200);gap:var(--space-200);justify-content:space-between}copilot-feedback-panel>p{margin:0}copilot-feedback-panel .dialog-footer{display:flex;gap:var(--space-100)}copilot-feedback-panel vaadin-select,copilot-feedback-panel vaadin-text-area,copilot-feedback-panel vaadin-text-field{padding-top:0;--lumo-text-field-size: 1.75rem;--vaadin-input-field-label-font-size: var(--font-size-2);--vaadin-input-field-background: none;--vaadin-input-field-border-color: transparent;--vaadin-input-field-border-width: 1px;--vaadin-input-field-border-color: var(--border-color-high-contrast);--vaadin-input-field-hover-highlight: var(--gray-100);--vaadin-input-field-hover-highlight-opacity: 1}copilot-feedback-panel vaadin-text-area{max-height:7em}copilot-feedback-panel vaadin-text-area>textarea{padding:var(--space-100) 0;font:var(--font-xsmall)}copilot-feedback-panel vaadin-text-area:hover::part(input-field){background-color:var(--gray-100)}copilot-feedback-panel vaadin-text-field>input{font:var(--font-xsmall)}copilot-feedback-panel vaadin-select::part(input-field){border-radius:var(--radius-1);flex:1;padding:0 var(--space-50)}vaadin-select-overlay[theme=feedback]::part(overlay){--color-high-contrast: var(--gray-500)}copilot-feedback-panel vaadin-select[focus-ring]::part(input-field){box-shadow:none;outline:2px solid var(--selection-color);outline-offset:-2px}copilot-feedback-panel vaadin-select-value-button{padding:0 var(--space-50)}copilot-feedback-panel vaadin-select-item{--_lumo-selected-item-height: 1.75rem;--_lumo-selected-item-padding: 0;font:var(--font-xsmall)}copilot-feedback-panel vaadin-select-item:hover{background:none}";
var w = Object.defineProperty, $ = Object.getOwnPropertyDescriptor, o = (e, a, l, n) => {
  for (var t = n > 1 ? void 0 : n ? $(a, l) : a, s = e.length - 1, r; s >= 0; s--)
    (r = e[s]) && (t = (n ? r(a, l, t) : r(t)) || t);
  return n && t && w(a, l, t), t;
};
const c = "https://github.com/vaadin/copilot/issues/new", A = "?template=feature_request.md&title=%5BFEATURE%5D", P = "A short, concise description of the bug and why you consider it a bug. Any details like exceptions and logs can be helpful as well.", T = "Please provide as many details as possible, this will help us deliver a fix as soon as possible.%0AThank you!%0A%0A%23%23%23 Description of the Bug%0A%0A{description}%0A%0A%23%23%23 Expected Behavior%0A%0AA description of what you would expect to happen. (Sometimes it is clear what the expected outcome is if something does not work, other times, it is not super clear.)%0A%0A%23%23%23 Minimal Reproducible Example%0A%0AWe would appreciate the minimum code with which we can reproduce the issue.%0A%0A%23%23%23 Versions%0A{versionsInfo}";
let i = class extends o$1 {
  constructor() {
    super(), this.description = "", this.items = [
      {
        label: "Report a Bug",
        value: "bug",
        ghTitle: "[BUG]"
      },
      {
        label: "Ask a Question",
        value: "question",
        ghTitle: "[QUESTION]"
      },
      {
        label: "Share an Idea",
        value: "idea",
        ghTitle: "[FEATURE]"
      }
    ];
  }
  render() {
    return _r`<style>
        ${x}</style
      >${this.renderContent()}${this.renderFooter()}`;
  }
  firstUpdated() {
    y(this);
  }
  renderContent() {
    return this.message === void 0 ? _r`
          <p>
            Your insights are incredibly valuable to us. Whether you’ve encountered a hiccup, have questions, or ideas
            to make our platform better, we're all ears! If you wish, leave your email and we’ll get back to you. You
            can even share your code snippet with us for a clearer picture.
          </p>
          <vaadin-select
            label="What's on your mind?"
            theme="feedback"
            .items="${this.items}"
            .value="${this.items[0].value}"
            @value-changed=${(e) => {
      this.type = e.detail.value;
    }}>
          </vaadin-select>
          <vaadin-text-area
            .value="${this.description}"
            @keydown=${this.keyDown}
            @focus=${() => {
      this.descriptionField.invalid = false, this.descriptionField.placeholder = "";
    }}
            @value-changed=${(e) => {
      this.description = e.detail.value;
    }}
            label="Tell Us More"
            helper-text="Describe what you're experiencing, wondering about, or envisioning. The more you share, the better we can understand and act on your feedback"></vaadin-text-area>
          <vaadin-text-field
            @keydown=${this.keyDown}
            @value-changed=${(e) => {
      this.email = e.detail.value;
    }}
            id="email"
            label="Your Email (Optional)"
            helper-text="Leave your email if you’d like us to follow up. Totally optional, but we’d love to keep the conversation going."></vaadin-text-field>
        ` : _r`<p>${this.message}</p>`;
  }
  renderFooter() {
    return this.message === void 0 ? _r`
          <div class="dialog-footer">
            <vaadin-button
              theme="tertiary"
              @click="${() => $$1.emit("system-info-with-callback", {
      callback: (e) => this.openGithub(e, this),
      notify: false
    })}">
              <span style="display: flex" slot="prefix">${r$1.github}</span>
              Create GitHub issue
            </vaadin-button>
            <div style="flex-grow: 1"></div>
            <vaadin-button theme="tertiary" @click="${this.close}">Cancel</vaadin-button>
            <vaadin-button theme="primary" @click="${this.submit}">Submit</vaadin-button>
          </div>
        ` : _r` <div class="footer">
          <vaadin-button @click="${this.close}">Close</vaadin-button>
        </div>`;
  }
  close() {
    Jt.updatePanel("copilot-feedback-panel", {
      floating: false
    });
  }
  submit() {
    if (this.description.trim() === "") {
      this.descriptionField.invalid = true, this.descriptionField.placeholder = "Please tell us more before sending", this.descriptionField.value = "";
      return;
    }
    const e = {
      description: this.description,
      email: this.email,
      type: this.type
    };
    je(`${rt}feedback`, e), this.parentNode?.style.setProperty("--section-height", "130px"), this.message = "Thank you for sharing feedback.";
  }
  keyDown(e) {
    (e.key === "Backspace" || e.key === "Delete") && e.stopPropagation();
  }
  openGithub(e, a) {
    if (this.type === "idea") {
      window.open(`${c}${A}`);
      return;
    }
    const l = e.replace(/\n/g, "%0A"), n = `${a.items.find((r) => r.value === this.type)?.ghTitle}`, t = a.description !== "" ? a.description : P, s = T.replace("{description}", t).replace("{versionsInfo}", l);
    window.open(`${c}?title=${n}&body=${s}`, "_blank")?.focus();
  }
};
o([
  g()
], i.prototype, "description", 2);
o([
  g()
], i.prototype, "type", 2);
o([
  g()
], i.prototype, "email", 2);
o([
  g()
], i.prototype, "message", 2);
o([
  g()
], i.prototype, "items", 2);
o([
  p("vaadin-text-area")
], i.prototype, "descriptionField", 2);
i = o([
  Ya("copilot-feedback-panel")
], i);
const F = {
  header: "Help Us Improve!",
  expanded: true,
  expandable: false,
  draggable: true,
  panelOrder: 0,
  floating: false,
  tag: "copilot-feedback-panel",
  width: 500,
  height: 500,
  floatingPosition: {
    top: 50,
    left: 50
  }
}, D = {
  init(e) {
    e.addPanel(F);
  }
};
window.Vaadin.copilot.plugins.push(D);
export {
  i as CopilotFeedbackPanel
};
