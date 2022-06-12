import {AmbientLight, AxesHelper, DirectionalLight, PerspectiveCamera, Scene, WebGLRenderer} from "three";
import {OrbitControls} from "three/examples/jsm/controls/OrbitControls";
import {HouseFactory} from "./factories/house.factory";

const scene = new Scene();

// Set up lights
const ambientLight = new AmbientLight(0x404040);
const directionalLight = new DirectionalLight(0xFFFFFF);
directionalLight.position.set(500, 500, 500);
scene.add(ambientLight, directionalLight);

// Set up renderer
const renderer = new WebGLRenderer({antialias: true});
renderer.setSize(window.innerWidth, window.innerHeight);
renderer.setPixelRatio(window.devicePixelRatio);

// Set up camera
const fov = 50;
const aspect = window.innerWidth / window.innerHeight;

const camera = new PerspectiveCamera(fov, aspect);
const orbitControls = new OrbitControls(camera, renderer.domElement);
camera.position.set(400, 400, 700);
orbitControls.update();

// Add house to scene
const house = HouseFactory();
scene.add(house);

document.body.appendChild(renderer.domElement);

// Dev options
const axesHelper = new AxesHelper(window.innerWidth);
scene.add(axesHelper);

function animate() {
    requestAnimationFrame(animate);
    orbitControls.update();

    renderer.render(scene, camera);
}

animate();
